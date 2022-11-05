package com.logistica.api.controller;

import com.logistica.api.assembler.AssemblerDelivery;
import com.logistica.api.model.AddresseeModel;
import com.logistica.api.model.DeliveryModel;
import com.logistica.domain.model.Delivery;
import com.logistica.domain.repository.DeliveryRepository;
import com.logistica.domain.services.DeliveryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private DeliveryRepository deliveryRepository;
    private DeliveryService deliveryService;
    private AssemblerDelivery assemblerDelivery;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryModel createDelivery(@Valid @RequestBody Delivery delivery) {
        Delivery createDelivery = deliveryService.requestDelivery(delivery);
        return assemblerDelivery.toModel(createDelivery);
    }

    @GetMapping
    public List<DeliveryModel> deliveryList() {
        return assemblerDelivery.toCollectionModel(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryModel> findDeliveryId(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .map(delivery -> ResponseEntity.ok(assemblerDelivery.toModel(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }
}