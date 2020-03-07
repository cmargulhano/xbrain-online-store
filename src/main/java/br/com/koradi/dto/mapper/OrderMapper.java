package br.com.koradi.dto.mapper;

import br.com.koradi.controller.v1.request.OrderRequest;
import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.service.CustomerService;
import br.com.koradi.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Order Mapper
 *
 * @author Cláudio Margulhano
 */
@Component
public class OrderMapper {

  @Autowired private CustomerService customerService;
  @Autowired private ProductServiceImpl productService;

  /**
   * Maps {@link OrderRequest} to {@link OrderDto}
   *
   * @param order {@link OrderRequest}
   * @return {@link OrderDto}
   */
  public OrderDto of(OrderRequest order) {
    OrderDto orderDto = new OrderDto();
    for (String productId : order.getProducts()) {
      orderDto.addProduct(productService.findById(productId));
    }
    CustomerDto customerDto = customerService.findCustomerById(order.getCustomerId());
    orderDto.setCustomerId(order.getCustomerId());
    return orderDto;
  }
}
