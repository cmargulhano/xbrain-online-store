package br.com.koradi.service;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.customer.Address;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.order.Order;
import br.com.koradi.model.order.OrderProduct;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.CustomerRepository;
import br.com.koradi.repository.OrderProductRepository;
import br.com.koradi.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.koradi.exception.EnterpriseException.throwException;
import static br.com.koradi.exception.EntityType.CUSTOMER;
import static br.com.koradi.exception.ExceptionType.DUPLICATE_ENTITY;
import static br.com.koradi.exception.ExceptionType.ENTITY_NOT_FOUND;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;

/** @author Cláudio Margulhano */
@Component
public class OrderServiceImpl implements OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private OrderProductRepository orderProductRepository;

  @Autowired private ModelMapper modelMapper;

  @Override
  public OrderDto create(OrderDto orderDto) {
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

    return modelMapper.map(order, OrderDto.class);
  }

  @Override
  public OrderDto findById(String id) {
    Order order = orderRepository.findById(id).get();
    OrderDto orderDto = modelMapper.map(order, OrderDto.class);
    orderProductRepository
        .findAllByOrderId(id)
        .forEach(
            orderProduct -> {
              orderDto.addProduct(modelMapper.map(orderProduct.getProduct(), ProductDto.class));
            });
    return orderDto;
  }
}
