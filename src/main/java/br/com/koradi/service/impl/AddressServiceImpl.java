package br.com.koradi.service.impl;

import br.com.koradi.dto.model.AddressDto;
import br.com.koradi.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

/**
 * Address service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class AddressServiceImpl implements AddressService {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  private static final String HTTPS_VIACEP_COM_BR_WS_S_JSON = "https://viacep.com.br/ws/%s/json/";
  @Autowired private RestTemplate restTemplate;

  /**
   * Finds address by zip code
   *
   * @param zipCode zip code
   * @return {@link AddressDto} address
   */
  @Override
  public AddressDto findByZipCode(String zipCode, String number) {
    String uri = format(HTTPS_VIACEP_COM_BR_WS_S_JSON, zipCode);
    try {
      ResponseEntity<AddressDto> addressDto = restTemplate.getForEntity(uri, AddressDto.class);
      return addressDto.getBody().setZipCode(zipCode).setNumber(number);
    } catch (HttpClientErrorException.BadRequest badRequest) {
      logger.info("Address not found - Zip code: " + zipCode);
    }
    return new AddressDto().setZipCode(zipCode);
  }
}
