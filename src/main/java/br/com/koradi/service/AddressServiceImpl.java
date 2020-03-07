package br.com.koradi.service;

import br.com.koradi.dto.model.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Component
public class AddressServiceImpl implements AddressService {
  private static final String HTTPS_VIACEP_COM_BR_WS_S_JSON = "https://viacep.com.br/ws/%s/json/";
  @Autowired private RestTemplate restTemplate;

  @Override
  public AddressDto findByZipCode(String zipCode) {
    String uri = format(HTTPS_VIACEP_COM_BR_WS_S_JSON, zipCode);
    ResponseEntity<AddressDto> result = restTemplate.getForEntity(uri, AddressDto.class);
    return result.getBody();
  }
}
