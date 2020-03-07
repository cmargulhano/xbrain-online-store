package br.com.koradi.controller.v1.api;

import br.com.koradi.controller.v1.request.CustomerRequest;
import br.com.koradi.dto.model.AddressDto;
import br.com.koradi.dto.model.CustomerDto;
import br.com.koradi.dto.response.Response;
import br.com.koradi.service.CustomerService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.koradi.dto.mapper.CustomerMapper.toCustomerDto;

/**
 * Customer controller
 *
 * @author Cláudio Margulhano
 */
@RestController
@RequestMapping("/api/v1/customer")
@Api(
    value = "virtual-store-application",
    description = "Operations pertaining to virtual-store application")
public class CustomerController {
  @Autowired private CustomerService customerService;
  @Autowired private ModelMapper modelMapper;

  /**
   * Creates new customer
   *
   * @param customerRequest {@link CustomerRequest}
   * @return {@link Response}
   */
  @PostMapping
  public Response create(@RequestBody @Valid CustomerRequest customerRequest) {
    return Response.ok().setPayload(customerService.create(toCustomerDto(customerRequest)));
  }

  /**
   * Updates customer
   *
   * @param customerRequest {@link CustomerRequest}
   * @return {@link Response}
   */
  @PutMapping
  public Response update(@RequestBody @Valid CustomerRequest customerRequest) {
    return Response.ok().setPayload(customerService.update(toCustomerDto(customerRequest)));
  }

  /**
   * Finds all customers
   *
   * @param page {@link Pageable} page
   * @param assembler {@link PagedResourcesAssembler} assembler
   * @return {@link Response} all customers
   */
  @GetMapping
  public PagedResources<Resource<CustomerDto>> findAll(
      Pageable page, PagedResourcesAssembler<CustomerDto> assembler) {
    return assembler.toResource(customerService.listAll(page));
  }

  /**
   * Finds customer by id
   *
   * @param id Id
   * @return {@link Response} address
   */
  @GetMapping("/address")
  public Response address(@RequestParam String id) {
    return Response.ok()
        .setPayload(
            modelMapper.map(
                this.customerService.findCustomerById(id).getAddress(), AddressDto.class));
  }
}
