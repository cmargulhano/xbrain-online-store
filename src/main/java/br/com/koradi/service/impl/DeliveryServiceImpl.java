package br.com.koradi.service.impl;

import br.com.koradi.dto.mapper.DeliveryMapper;
import br.com.koradi.dto.model.DeliveryDto;
import br.com.koradi.dto.model.OrderDto;
import br.com.koradi.model.delivery.Delivery;
import br.com.koradi.repository.DeliveryRepository;
import br.com.koradi.service.DeliveryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Delivery Service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class DeliveryServiceImpl implements DeliveryService {
  @Autowired private DeliveryRepository deliveryRepository;
  @Autowired private DeliveryMapper deliveryMapper;
  @Autowired private ModelMapper modelMapper;

  @Override
  public DeliveryDto create(OrderDto orderDto) {
    Delivery delivery = deliveryMapper.of(orderDto);
    return modelMapper.map(deliveryRepository.save(delivery), DeliveryDto.class);
  }

  @Override
  public Page<DeliveryDto> listAll(Pageable pageable) {
    Page<Delivery> customers = deliveryRepository.findAll(pageable);
    return customers.map(this::toDeliveryDto);
  }

  private DeliveryDto toDeliveryDto(Delivery delivery) {
    return deliveryMapper.of(delivery);
  }
}
