package br.com.koradi.repository;

import br.com.koradi.model.delivery.Delivery;
import org.springframework.data.repository.CrudRepository;

/**
 * Delivery Repository
 *
 * @author Cláudio Margulhano
 */
public interface DeliveryRepository extends CrudRepository<Delivery, String> {}
