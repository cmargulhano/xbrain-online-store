package br.com.koradi.controller.v1.request;

import br.com.koradi.model.customer.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/** @author Cláudio Margulhano */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {
  private String id;
  private String fullName;
  private String email;
  private LocalDate birthDate;
  private GenderType gender;
  private String phoneNumber;
  private String mobileNumber;
  private String zipCode;
  private String addressNumber;
}
