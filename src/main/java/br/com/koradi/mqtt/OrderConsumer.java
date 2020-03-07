package br.com.koradi.mqtt;

import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Consume order from MQTT
 *
 * @author Cláudio Margulhano
 */
@Component
public class OrderConsumer {
  private Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
  @Autowired private DeliveryService deliveryService;

  /**
   * Consume order
   *
   * @param orderDto {@link OrderDto} order
   */
  @RabbitListener(queues = {"${br.com.koradi.rabbitmq.queue}"})
  public void receive(@Payload OrderDto orderDto) {
    logger.info("Order: " + orderDto);
    deliveryService.create(orderDto);
  }
}
