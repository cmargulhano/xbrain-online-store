package br.com.koradi.dto.mapper;

import br.com.koradi.controller.v1.request.CustomerRequest;
import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.model.customer.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Customer Mapper
 *
 * @author Cláudio Margulhano
 */
@Component
public class CustomerMapper {

  @Autowired private ModelMapper mapper;

  /**
   * Maps {@link Customer} to {@link CustomerDto}
   *
   * @param customer {@link Customer}
   * @return {@link CustomerDto}
   */
  public CustomerDto of(Customer customer) {
    CustomerDto customerDto =
        new CustomerDto()
            .setId(customer.getId())
            .setFullName(customer.getFullName())
            .setEmail(customer.getEmail())
            .setBirthDate(customer.getBirthDate())
            .setGender(customer.getGender())
            .setPhoneNumber(customer.getPhoneNumber())
            .setMobileNumber(customer.getMobileNumber());
    return customerDto;
  }

  /**
   * Maps {@link CustomerRequest} to {@link CustomerDto}
   *
   * @param customer {@link CustomerRequest}
   * @return {@link CustomerDto}
   */
  public CustomerDto of(CustomerRequest customer) {
    return new CustomerDto()
        .setId(customer.getId())
        .setFullName(customer.getFullName())
        .setEmail(customer.getEmail())
        .setBirthDate(customer.getBirthDate())
        .setGender(customer.getGender())
        .setPhoneNumber(customer.getPhoneNumber())
        .setPhoneNumber(customer.getPhoneNumber())
        .setMobileNumber(customer.getMobileNumber())
        .setZipCode(customer.getZipCode())
        .setAddressNumber(customer.getAddressNumber());
  }

  /**
   * Merge {@link Customer} and {@link CustomerDto}
   *
   * @param customer {@link Customer}
   * @param customerDto {@link CustomerDto}
   * @return {@link Customer}
   */
  public Customer of(Customer customer, CustomerDto customerDto) {
    if (customerDto.getBirthDate() != null) {
      customer.setBirthDate(customerDto.getBirthDate());
    }
    if (customerDto.getFullName() != null && !"".equals(customerDto.getFullName())) {
      customer.setFullName(customerDto.getFullName());
    }
    if (customerDto.getGender() != null) {
      customer.setGender(customerDto.getGender());
    }
    if (customerDto.getPhoneNumber() != null && !"".equals(customerDto.getPhoneNumber())) {
      customer.setPhoneNumber(customerDto.getPhoneNumber());
    }
    if (customerDto.getMobileNumber() != null && !"".equals(customerDto.getMobileNumber())) {
      customer.setMobileNumber(customerDto.getMobileNumber());
    }
    return customer;
  }
}
