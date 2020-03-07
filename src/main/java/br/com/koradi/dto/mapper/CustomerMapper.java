package br.com.koradi.dto.mapper;

import br.com.koradi.controller.v1.request.CustomerRequest;
import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.model.customer.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/** @author Cláudio Margulhano */
@Component
public class CustomerMapper {

  public static CustomerDto toCustomerDto(Customer customer) {
    CustomerDto user =
        new CustomerDto()
            .setFullName(customer.getFullName())
            .setEmail(customer.getEmail())
            .setBirthDate(customer.getBirthDate())
            .setGender(customer.getGender())
            .setPhoneNumber(customer.getPhoneNumber())
            .setMobileNumber(customer.getMobileNumber());
    if (customer.getOrders() != null && customer.getOrders().size() > 0) {
      user.setOrders(
          new HashSet<OrderDto>(
              customer.getOrders().stream()
                  .map(role -> new ModelMapper().map(role, OrderDto.class))
                  .collect(Collectors.toSet())));
    }
    return user;
  }

  public static CustomerDto toCustomerDto(CustomerRequest customer) {
    return new CustomerDto()
        .setFullName(customer.getFullName())
        .setEmail(customer.getEmail())
        .setBirthDate(customer.getBirthDate())
        .setGender(customer.getGender())
        .setPhoneNumber(customer.getPhoneNumber())
        .setPhoneNumber(customer.getPhoneNumber())
        .setMobileNumber(customer.getMobileNumber());
  }
}
