package br.com.koradi.service.impl;

import br.com.koradi.dto.model.AddressDto;
import br.com.koradi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Address service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class AddressServiceImpl implements AddressService {
  private static final String HTTPS_VIACEP_COM_BR_WS_S_JSON = "https://viacep.com.br/ws/%s/json/";
  @Autowired private RestTemplate restTemplate;

  /**
   * Finds address by zip code
   *
   * @param zipCode zip code
   * @return {@link AddressDto} address
   */
  @Override
  public AddressDto findByZipCode(String zipCode) {
    String uri = format(HTTPS_VIACEP_COM_BR_WS_S_JSON, zipCode);
    ResponseEntity<AddressDto> addressDto = restTemplate.getForEntity(uri, AddressDto.class);
    return requireNonNull(addressDto.getBody()).setZipCode(zipCode);
  }
}
