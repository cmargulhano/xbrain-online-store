package br.com.koradi.controller.v1.request;

import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.model.ProductDto;
import br.com.koradi.model.customer.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

/** @author Cláudio Margulhano */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
  private String customerId;
  private List<String> products;
}
