package br.com.koradi.service.impl;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.model.customer.Address;
import br.com.koradi.model.customer.Customer;
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

  @Autowired private ModelMapper modelMapper;

  @Autowired private AddressService addressService;

  @Override
  public CustomerDto create(CustomerDto customerDto) {
    Optional<Customer> customer =
        ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
    if (!customer.isPresent()) {
      customer = ofNullable(modelMapper.map(customerDto, Customer.class));
      Optional<Address> address =
          ofNullable(
              modelMapper.map(
                  addressService.findByZipCode(customerDto.getZipCode()), Address.class));
      Customer customerModel = customer.get();
      customerModel.setAddress(address.get());
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
        Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
    if (customer.isPresent()) {
      final String id = customer.get().getId();
      customer = ofNullable(modelMapper.map(customerDto, Customer.class));
      Customer customerModel = customer.get();
      customerModel.setId(id);
      return modelMapper.map(customerRepository.save(customer.get()), CustomerDto.class);
    }
    throw throwException(CUSTOMER, ENTITY_NOT_FOUND, customerDto.getFullName());
  }

  @Override
  public Page<CustomerDto> listAll(Pageable pageable) {
    Page<Customer> customers = customerRepository.findAll(pageable);
    return customers.map(this::toCustomerDto);
  }

  private CustomerDto toCustomerDto(Customer customer) {
    customer.setOrders(null);
    return modelMapper.map(customer, CustomerDto.class);
  }
}
