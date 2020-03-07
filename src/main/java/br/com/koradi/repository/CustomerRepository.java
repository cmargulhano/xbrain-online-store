package br.com.koradi.repository;

import br.com.koradi.model.customer.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Customer Repository
 *
 * @author Cláudio Margulhano
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {

  Customer findByEmail(String email);
}
