package br.com.koradi.repository;

import br.com.koradi.model.customer.Address;
import br.com.koradi.model.customer.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Address Repository
 *
 * @author Cláudio Margulhano
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, String> {
}
