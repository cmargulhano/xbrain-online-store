package br.com.koradi.controller.v1.request;

import br.com.koradi.model.customer.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/** @author Cláudio Margulhano */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {
  @NotEmpty(message = "{constraints.NotEmpty.message}")
  private String fullName;
  private String email;
  private LocalDate birthDate;
  private GenderType gender;
  private String phoneNumber;
  private String mobileNumber;
  private String zipCode;
}
