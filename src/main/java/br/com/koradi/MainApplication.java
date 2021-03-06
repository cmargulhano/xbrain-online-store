package br.com.koradi;

import br.com.koradi.model.customer.Address;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.order.Order;
import br.com.koradi.model.order.OrderProduct;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.AddressRepository;
import br.com.koradi.repository.CustomerRepository;
import br.com.koradi.repository.OrderRepository;
import br.com.koradi.repository.ProductRepository;
import br.com.koradi.service.AddressService;
import br.com.koradi.service.impl.RabbitMQSender;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.*;

import static br.com.koradi.model.customer.GenderType.MALE;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.of;
import static java.time.LocalDateTime.now;
import static java.util.Calendar.MAY;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;

/**
 * Application Starter
 *
 * @author Cláudio Margulhano
 */
@SpringBootApplication
@EnableRabbit
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  CommandLineRunner init(
      ProductRepository productRepository,
      OrderRepository orderRepository,
      CustomerRepository customerRepository,
      AddressRepository addressRepository,
      AddressService addressService,
      RabbitMQSender rabbitMQSender,
      ModelMapper modelMapper) {
    return args -> {

      // Load some data
      // In real app we could use liquibase

      Map<String, BigDecimal> productList =
          new HashMap() {
            {
              put("Sport Shoes", valueOf(100));
              put("Winter boots children", valueOf(120));
              put("Red sneaker", valueOf(140));
            }
          };

      List<Product> products = new ArrayList<Product>();
      productList.forEach(
          (name, price) -> {
            Product product = new Product().setName(name).setPrice(price);
            productRepository.save(product);
            products.add(product);
          });

      Order order = new Order().setPrice(products.get(0).getPrice()).setOrderDate(now());
      order = orderRepository.save(order);

      OrderProduct orderProduct =
          OrderProduct.builder().product(products.get(0)).order(order).build();

      order.setOrderProducts(new HashSet<>(singletonList(orderProduct)));
      order = orderRepository.save(order);

      Address address =
          ofNullable(
                  modelMapper.map(addressService.findByZipCode("12236896", "126"), Address.class))
              .get();
      addressRepository.save(address);

      Customer customer =
          new Customer()
              .setFullName("Cláudio Margulhano")
              .setEmail("cmargulhano@gmail.com")
              .setBirthDate(of(1979, MAY, 15))
              .setGender(MALE)
              .setPhoneNumber("(12)99667-3166")
              .setMobileNumber("(12)99667-3166")
              .setAddress(address);
      customerRepository.save(customer);
      order.setCustomer(customer);
      orderRepository.save(order);

      rabbitMQSender.send(order.getId());
    };
  }
}
