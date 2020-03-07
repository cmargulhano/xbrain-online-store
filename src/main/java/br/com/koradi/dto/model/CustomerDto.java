package br.com.koradi.dto.model;

import br.com.koradi.model.customer.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/** @author Cláudio Margulhano */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
  private String id;
  private String fullName;
  private LocalDate birthDate;
  private GenderType gender;
  private String phoneNumber;
  private String mobileNumber;
  private String email;
  private String zipCode;
  private AddressDto address;
  private Set<OrderDto> orders;
}
