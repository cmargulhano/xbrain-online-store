package br.com.koradi.service;

import br.com.koradi.dto.model.DeliveryDto;
import br.com.koradi.dto.model.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

  /**
   * Lists all delivered orders
   *
   * @param pageable {@link Pageable}
   * @return {@link Page}
   */
  public Page<DeliveryDto> listAll(Pageable pageable);
}
