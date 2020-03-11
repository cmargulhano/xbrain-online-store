package br.com.koradi.controller.v1.api;

import br.com.koradi.controller.v1.request.OrderRequest;
import br.com.koradi.dto.mapper.OrderMapper;
import br.com.koradi.dto.response.Response;
import br.com.koradi.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Order controller
 *
 * @author Cláudio Margulhano
 */
@RestController
@RequestMapping("/api/v1/order")
@Api(
    value = "virtual-store-application",
    description = "Operations pertaining to virtual-store application")
public class OrderController {
  @Autowired private OrderService orderService;
  @Autowired private OrderMapper orderMapper;
  /**
   * Creates new order
   *
   * @param orderRequest {@link OrderRequest}
   * @return {@link Response}
   */
  @ApiOperation(value = "Creates new order", response = Response.class)
  @PostMapping
  public Response create(@RequestBody @Valid OrderRequest orderRequest) {
    return Response.ok().setPayload(orderService.create(orderMapper.of(orderRequest)));
  }

  /**
   * Gets order
   *
   * @param id Order id
   * @return {@link Response}
   */
  @ApiOperation(value = "Gets order by id", response = Response.class)
  @GetMapping("{id}")
  public Response getOrder(@PathVariable String id) {
    return Response.ok().setPayload(orderService.findById(id));
  }
}
