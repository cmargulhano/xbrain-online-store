package br.com.koradi.service;

import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.repository.AddressRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

  @Autowired private AmqpTemplate rabbitTemplate;

  @Autowired private OrderService orderService;

  @Value("${br.com.koradi.rabbitmq.exchange}")
  private String exchange;

  @Value("${br.com.koradi.rabbitmq.routingkey}")
  private String routingkey;

  public void send(String id) {
    OrderDto order = orderService.findById(id);
    rabbitTemplate.convertAndSend(exchange, routingkey, order);
  }
}
