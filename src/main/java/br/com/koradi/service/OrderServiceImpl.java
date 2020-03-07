package br.com.koradi.service;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.order.Order;
import br.com.koradi.model.order.OrderProduct;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.OrderProductRepository;
import br.com.koradi.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;

/** @author Cláudio Margulhano */
@Component
public class OrderServiceImpl implements OrderService {

  @Autowired private CustomerService customerService;

  @Autowired private OrderRepository orderRepository;

  @Autowired private OrderProductRepository orderProductRepository;

  @Autowired private ModelMapper modelMapper;

  @Autowired private RabbitMQSender rabbitMQSender;

  @Override
  public OrderDto create(OrderDto orderDto) {
    // TODO: Check if order and product exists
    Order orderModel = modelMapper.map(orderDto, Order.class);
    orderModel.setCustomer(new Customer(orderDto.getCustomerId()));
    double price =
        orderDto.getProducts().stream()
            .mapToDouble(product -> product.getPrice().doubleValue())
            .sum();
    orderModel.setPrice(valueOf(price)).setOrderDate(now());
    Order order = orderRepository.save(orderModel);

    orderDto
        .getProducts()
        .forEach(
            product -> {
              OrderProduct orderProduct =
                  OrderProduct.builder()
                      .product(modelMapper.map(product, Product.class))
                      .order(order)
                      .build();
              orderProductRepository.save(orderProduct);
            });

    orderDto = modelMapper.map(order, OrderDto.class);

    rabbitMQSender.send(orderDto.getId());

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
}
