package br.com.koradi.service;

import br.com.koradi.dto.model.DeliveryDto;
import br.com.koradi.dto.model.OrderDto;

/**
 * Delivery Service
 *
 * @author Cláudio Margulhano
 */
public interface DeliveryService {
  /**
   * Saves delivery
   *
   * @param orderDto {@link OrderDto}
   * @return {@link OrderDto}
   */
  public DeliveryDto create(OrderDto orderDto);
}
