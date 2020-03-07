package br.com.koradi.repository;

import br.com.koradi.model.order.OrderProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Order Product Repository
 *
 * @author Cláudio Margulhano
 */
public interface OrderProductRepository extends CrudRepository<OrderProduct, String> {
  public List<OrderProduct> findAllByOrderId(String orderId);
}
