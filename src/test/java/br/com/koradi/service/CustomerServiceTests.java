package br.com.koradi.service;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTests {
  @Autowired private CustomerService customerService;
  @Autowired private CustomerRepository customerRepository;

  @Test
  public void findCustomer() {
    Customer customer = customerRepository.findByEmail("cmargulhano@gmail.com");
    assertThat(customer).isNotNull();
  }

  @Test
  public void listCustomers() {
    Page<CustomerDto> page = customerService.listAll(of(0, 10));
    assertThat(page).isNotNull();
    assertThat(page.getContent()).isNotNull();
    assertThat(page.getContent().size()).isGreaterThan(0);
  }
}
