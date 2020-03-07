package br.com.koradi.repository;

import br.com.koradi.model.order.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order Repository
 *
 * @author Cláudio Margulhano
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {

  List<Order> findAllByOrderDate(
      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime orderDate, Pageable pageable);
}
