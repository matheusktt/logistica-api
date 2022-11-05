package com.logisticaapi.controller;

import com.logisticaapi.domain.model.Delivery;
import com.logisticaapi.domain.services.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery newDelivery(@RequestBody Delivery delivery) {
        return deliveryService.requestDelivery(delivery);
    }
}