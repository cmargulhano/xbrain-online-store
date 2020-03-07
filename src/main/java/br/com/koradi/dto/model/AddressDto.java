package br.com.koradi.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddressDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("cep")
  private String zipCode;

  @JsonProperty("logradouro")
  private String complement;

  @JsonProperty("bairro")
  private String neighborhood;

  @JsonProperty("localidade")
  private String locality;

  @JsonProperty("uf")
  private String federalUnit;

  @JsonProperty("unidade")
  private String unit;

  @JsonProperty("number")
  private String number;
}
