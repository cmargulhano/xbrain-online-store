package br.com.koradi.service;

import br.com.koradi.dto.model.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Cláudio Margulhano */
public interface CustomerService {
  /**
   * Saves customer
   *
   * @param customerDto {@link CustomerDto}
   * @return {@link CustomerDto}
   */
  CustomerDto create(CustomerDto customerDto);

  /**
   * Finds customer by id
   *
   * @param id Id
   * @return {@link CustomerDto}
   */
  public CustomerDto findCustomerById(String id);

  /**
   * Updates customer
   *
   * @param customerDto {@link CustomerDto}
   * @return @return {@link CustomerDto}
   */
  public CustomerDto update(CustomerDto customerDto);

  /**
   * List customers
   *
   * @param pageable {@link Pageable}
   * @return {@link Page}
   */
  public Page<CustomerDto> listAll(Pageable pageable);
}
