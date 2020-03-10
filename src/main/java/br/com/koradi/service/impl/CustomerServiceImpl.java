package br.com.koradi.service.impl;

import br.com.koradi.dto.mapper.CustomerMapper;
import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.model.customer.Address;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.repository.AddressRepository;
import br.com.koradi.repository.CustomerRepository;
import br.com.koradi.service.AddressService;
import br.com.koradi.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.koradi.exception.EnterpriseException.throwException;
import static br.com.koradi.exception.EntityType.ADDRESS;
import static br.com.koradi.exception.EntityType.CUSTOMER;
import static br.com.koradi.exception.ExceptionType.DUPLICATE_ENTITY;
import static br.com.koradi.exception.ExceptionType.ENTITY_NOT_FOUND;
import static java.util.Optional.ofNullable;

/**
 * Customer Service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class CustomerServiceImpl implements CustomerService {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private AddressRepository addressRepository;

  @Autowired private AddressService addressService;

  @Autowired private ModelMapper modelMapper;

  @Autowired private CustomerMapper customerMapper;

  @Override
  public CustomerDto create(CustomerDto customerDto) {
    Optional<Customer> customer =
        ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
    if (!customer.isPresent()) {
      Customer customerModel = modelMapper.map(customerDto, Customer.class);
      return modelMapper.map(customerRepository.save(customerModel), CustomerDto.class);
    }
    throw throwException(CUSTOMER, DUPLICATE_ENTITY, customerDto.getEmail());
  }

  @Override
  public CustomerDto findCustomerById(String id) {
    Optional<Customer> customer = customerRepository.findById(id);
    if (customer.isPresent()) {
      Customer customerModel = customer.get();
      return modelMapper.map(customerModel, CustomerDto.class);
    }
    throw throwException(CUSTOMER, ENTITY_NOT_FOUND, id);
  }

  @Override
  public CustomerDto update(CustomerDto customerDto) {
    Optional<Customer> customer =
        ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
    if (customer.isPresent()) {
      Customer customerModel = customerMapper.of(customer.get(), customerDto);
      customerModel.setAddress(saveAddress(customerDto).get());
      return modelMapper.map(customerRepository.save(customerModel), CustomerDto.class);
    }
    throw throwException(CUSTOMER, ENTITY_NOT_FOUND, customerDto.getFullName());
  }

  @Override
  public Page<CustomerDto> listAll(Pageable pageable) {
    Page<Customer> customers = customerRepository.findAll(pageable);
    return customers.map(this::toCustomerDto);
  }

  /**
   * Maps {@link Customer} to {@link CustomerDto}
   *
   * @param customer {@link Customer}
   * @return {@link CustomerDto}
   */
  private CustomerDto toCustomerDto(Customer customer) {
    return customerMapper.of(customer);
  }

  /**
   * Saves address
   *
   * @param customerDto {@link CustomerDto} customer
   * @return {@link Address} address
   */
  private Optional<Address> saveAddress(CustomerDto customerDto) {
    Optional<Address> address =
        ofNullable(
            modelMapper
                .map(
                    addressService.findByZipCode(
                        customerDto.getZipCode(), customerDto.getAddressNumber()),
                    Address.class)
                .setId(getAddressId(customerDto)));
    if (!address.isPresent() || address.get().getLocality() == null) {
      throw throwException(ADDRESS, ENTITY_NOT_FOUND, customerDto.getZipCode());
    }
    addressRepository.saveAndFlush(address.get());
    return address;
  }

  /**
   * Gets address' id
   *
   * @param customerDto {@link CustomerDto}
   * @return Address' id
   */
  private String getAddressId(CustomerDto customerDto) {
    String id = null;
    if (customerDto.getEmail() != null) {
      Customer customer = customerRepository.findByEmail(customerDto.getEmail());
      if (customer != null && customer.getAddress() != null) {
        id = customer.getAddress().getId();
      }
    }
    return id;
  }
}
