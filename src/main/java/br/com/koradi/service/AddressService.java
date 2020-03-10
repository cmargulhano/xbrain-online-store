package br.com.koradi.service;

import br.com.koradi.dto.model.AddressDto;

/**
 * Address Service
 *
 * @author Cláudio Margulhano
 */
public interface AddressService {
  /**
   * Finds address by zip code
   *
   * @param zipCode Zip code
   * @param number Address' number
   * @return {@link AddressDto}
   */
  public AddressDto findByZipCode(String zipCode, String number);
}
