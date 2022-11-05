package com.logisticaapi.controller;

import com.logisticaapi.domain.model.Delivery;
import com.logisticaapi.domain.repository.DeliveryRepository;
import com.logisticaapi.domain.services.DeliveryService;
import lombok.AllArgsConstructor;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery newDelivery(@Valid @RequestBody Delivery delivery) {
        return deliveryService.requestDelivery(delivery);
    }

    @GetMapping
    public List<Delivery> deliveryList() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<Delivery> findDeliveryId(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}