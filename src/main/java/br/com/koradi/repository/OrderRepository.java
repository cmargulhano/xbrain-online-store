package br.com.koradi.repository;

import br.com.koradi.model.order.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

/** @author Cláudio Margulhano */
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
  // List<Order> findAllByExpirationDate(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate
  // expirationDate);
}
