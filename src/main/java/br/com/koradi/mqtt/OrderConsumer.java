package br.com.koradi.mqtt;

import br.com.koradi.dto.model.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
  Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

  @RabbitListener(queues = {"${br.com.koradi.rabbitmq.queue}"})
  public void receive(@Payload OrderDto orderDto) {
    logger.info("Order: " + orderDto);
    //TODO: Save sent order
  }
}
