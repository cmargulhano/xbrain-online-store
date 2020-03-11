package br.com.koradi.dto.mapper;

import br.com.koradi.dto.model.DeliveryDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.delivery.Delivery;
import br.com.koradi.model.order.Order;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;

/**
 * Delivery Mapper
 *
 * @author Cláudio Margulhano
 */
@Component
public class DeliveryMapper {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired private ModelMapper modelMapper;

  /**
   * Maps {@link OrderDto} to {@link Delivery}
   *
   * @param orderDto {@link OrderDto} order
   * @return {@link Delivery}
   */
  public Delivery of(OrderDto orderDto) {
    Delivery delivery =
        new Delivery()
            .setPrice(orderDto.getPrice())
            .setOrderDate(now())
            .setOrder(new Order(orderDto.getId()))
            .setCustomer(modelMapper.map(orderDto.getCustomer(), Customer.class));
    return delivery;
  }

  public DeliveryDto of(Delivery delivery) {
    DeliveryDto deliveryDto = modelMapper.map(delivery, DeliveryDto.class);
    delivery
        .getOrder()
        .getOrderProducts()
        .forEach(
            orderProduct -> {
              deliveryDto.addProduct(modelMapper.map(orderProduct.getProduct(), ProductDto.class));
            });
    return deliveryDto;
  }
}
