package br.com.koradi.service;

import br.com.koradi.dto.model.ProductDto;

/**
 * Product Service
 *
 * @author Cláudio Margulhano
 */
public interface ProductService {
  /**
   * Finds product by id
   *
   * @param id Id
   * @return {@link ProductDto}
   */
  public ProductDto findById(String id);
}
