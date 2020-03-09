package br.com.koradi.service.impl;

import br.com.koradi.service.OrderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Sends order to message queue
 *
 * @author Cláudio Margulhano
 */
@Service
public class RabbitMQSender {

  @Autowired private AmqpTemplate rabbitTemplate;

  @Autowired private OrderService orderService;

  @Value("${br.com.koradi.rabbitmq.exchange}")
  private String exchange;

  @Value("${br.com.koradi.rabbitmq.routingkey}")
  private String routingkey;

  /**
   * Sends order
   *
   * @param orderId order id
   */
  public void send(String orderId) {
    rabbitTemplate.convertAndSend(exchange, routingkey, orderService.findById(orderId));
  }
}
