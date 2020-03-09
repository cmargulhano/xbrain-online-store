package br.com.koradi.service;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.AddressRepository;
import br.com.koradi.repository.CustomerRepository;
import br.com.koradi.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
  @Autowired private CustomerService customerService;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private ModelMapper mapper;
  @Autowired private AddressService addressService;
  @Autowired private ProductRepository productRepository;
  @Autowired private AddressRepository addressRepository;
  @Autowired private OrderService orderService;
  private Customer customer;

  @Before
  public void init() {
    this.customer = customerRepository.findByEmail("cmargulhano@gmail.com");
  }

  @Test
  public void createOrder() {
    CustomerDto customerDto = customerService.findCustomerById(customer.getId());
    assertThat(customerDto).isNotNull();
    Page<Product> products = productRepository.findAll(of(0, 10));

    OrderDto orderDto =
        new OrderDto()
            .setCustomerId(customer.getId())
            .setProducts(new HashSet<>(products.map(this::toProductDto).getContent()));

    assertThat(orderDto).isNotNull();

    OrderDto orderCreated = orderService.create(orderDto);
    assertThat(orderCreated).isNotNull();
  }

  private ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }
}
