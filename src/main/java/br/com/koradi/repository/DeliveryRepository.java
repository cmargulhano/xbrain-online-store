package br.com.koradi.repository;

import br.com.koradi.model.delivery.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Delivery Repository
 *
 * @author Cláudio Margulhano
 */
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, String> {}
