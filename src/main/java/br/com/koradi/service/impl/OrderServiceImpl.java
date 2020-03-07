package br.com.koradi.service.impl;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.order.Order;
import br.com.koradi.model.order.OrderProduct;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.OrderProductRepository;
import br.com.koradi.repository.OrderRepository;
import br.com.koradi.service.CustomerService;
import br.com.koradi.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;

/**
 * Order Service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class OrderServiceImpl implements OrderService {

  @Autowired private CustomerService customerService;

  @Autowired private OrderRepository orderRepository;

  @Autowired private OrderProductRepository orderProductRepository;

  @Autowired private ModelMapper modelMapper;

  @Autowired private RabbitMQSender rabbitMQSender;

  @Override
  public OrderDto create(OrderDto orderDto) {
    Order order = saveOrder(orderDto);
    orderDto = modelMapper.map(order, OrderDto.class);
    send(orderDto);
    return orderDto;
  }

  @Override
  public OrderDto findById(String id) {
    Order order = orderRepository.findById(id).get();
    OrderDto orderDto = modelMapper.map(order, OrderDto.class);
    CustomerDto customer = customerService.findCustomerById(order.getCustomer().getId());
    customer.setOrders(null);
    orderDto.setCustomer(customer);
    orderProductRepository
        .findAllByOrderId(id)
        .forEach(
            orderProduct -> {
              orderDto.addProduct(modelMapper.map(orderProduct.getProduct(), ProductDto.class));
            });
    return orderDto;
  }

  /**
   * Sends order to MQTT
   *
   * @param orderDto {@link OrderDto}
   */
  private void send(OrderDto orderDto) {
    rabbitMQSender.send(orderDto.getId());
  }

  /**
   * Saves order
   *
   * @param orderDto {@link OrderDto} order
   * @return {@link Order}
   */
  private Order saveOrder(OrderDto orderDto) {
    Order orderModel = modelMapper.map(orderDto, Order.class);
    orderModel.setCustomer(new Customer(orderDto.getCustomerId()));
    double price =
        orderDto.getProducts().stream()
            .mapToDouble(product -> product.getPrice().doubleValue())
            .sum();
    orderModel.setPrice(valueOf(price)).setOrderDate(now());
    Order order = orderRepository.save(orderModel);

    Set<OrderProduct> orderProducts = new HashSet<>();
    orderDto
        .getProducts()
        .forEach(
            product -> {
              OrderProduct orderProduct =
                  OrderProduct.builder()
                      .product(modelMapper.map(product, Product.class))
                      .order(order)
                      .build();
              orderProducts.add(orderProductRepository.save(orderProduct));
            });
    order.setOrderProducts(orderProducts);
    return orderRepository.save(order);
  }
}
