package com.logistica.api.assembler;

import com.logistica.api.model.DeliveryModel;
import com.logistica.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AssemblerDelivery {

    private ModelMapper modelMapper;

    public DeliveryModel toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryModel.class);
    }

    public List<DeliveryModel> toCollectionModel(List<Delivery> deliveries) {
        return deliveries.stream().map(this::toModel).collect(Collectors.toList());
    }
}