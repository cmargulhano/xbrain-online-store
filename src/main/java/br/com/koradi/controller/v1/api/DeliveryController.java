package br.com.koradi.controller.v1.api;

import br.com.koradi.dto.model.DeliveryDto;
import br.com.koradi.dto.response.Response;
import br.com.koradi.service.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Delivery controller
 *
 * @author Cláudio Margulhano
 */
@RestController
@RequestMapping("/api/v1/delivery")
@Api(
    value = "virtual-store-application",
    description = "Operations pertaining to virtual-store application")
public class DeliveryController {

  @Autowired private DeliveryService deliveryService;

  /**
   * Finds all delivered orders
   *
   * @param page {@link Pageable} page
   * @param assembler {@link PagedResourcesAssembler} assembler
   * @return {@link Response} all delivered orders
   */
  @ApiOperation(value = "Finds all delivered orders", response = Response.class)
  @GetMapping
  public PagedResources<Resource<DeliveryDto>> findAll(
      Pageable page, PagedResourcesAssembler<DeliveryDto> assembler) {
    return assembler.toResource(deliveryService.listAll(page));
  }
}
