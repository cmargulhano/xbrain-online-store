package br.com.koradi.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Order DTO
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
  private String id;
  private String customerId;
  private CustomerDto customer;
  private BigDecimal price;
  private Set<ProductDto> products;

  public void addProduct(ProductDto productDto) {
    if (products == null) {
      products = new HashSet<>();
    }
    products.add(productDto);
  }
}
