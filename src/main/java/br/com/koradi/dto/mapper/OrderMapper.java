package br.com.koradi.dto.mapper;

import br.com.koradi.controller.v1.request.OrderRequest;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.product.Product;
import br.com.koradi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** @author Cláudio Margulhano */
@Component
public class OrderMapper {

  @Autowired private ProductRepository productRepository;
  @Autowired private ModelMapper modelMapper;

  public OrderDto to(OrderRequest order) {
    OrderDto orderDto = new OrderDto();
    for (String productId : order.getProducts()) {
      Optional<Product> product = productRepository.findById(productId);
      orderDto.addProduct(modelMapper.map(product.get(), ProductDto.class));
    }
    orderDto.setCustomerId(order.getCustomerId());
    return orderDto;
  }
}
