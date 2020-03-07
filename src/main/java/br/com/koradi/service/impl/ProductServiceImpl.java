package br.com.koradi.service.impl;

import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.ProductRepository;
import br.com.koradi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.koradi.exception.EnterpriseException.throwException;
import static br.com.koradi.exception.EntityType.PRODUCT;
import static br.com.koradi.exception.ExceptionType.ENTITY_NOT_FOUND;

/**
 * Product service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class ProductServiceImpl implements ProductService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ModelMapper modelMapper;

  @Override
  public ProductDto findById(String id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isPresent()) {
      return modelMapper.map(product.get(), ProductDto.class);
    } else {
      throw throwException(PRODUCT, ENTITY_NOT_FOUND, id);
    }
  }
}
