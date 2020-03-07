package br.com.koradi.service;

import br.com.koradi.dto.model.OrderDto;

/**
 * Order Service
 *
 * @author Cláudio Margulhano
 */
public interface OrderService {
  /**
   * Saves order
   *
   * @param orderDto {@link OrderDto}
   * @return {@link OrderDto}
   */
  OrderDto create(OrderDto orderDto);

  /**
   * Finds order by id
   *
   * @param id Id
   * @return {@link OrderDto}
   */
  OrderDto findById(String id);
}
