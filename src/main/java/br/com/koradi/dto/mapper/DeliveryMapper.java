package br.com.koradi.dto.mapper;

import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.model.customer.Customer;
import br.com.koradi.model.delivery.Delivery;
import br.com.koradi.model.order.Order;
import org.modelmapper.ModelMapper;
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
}
