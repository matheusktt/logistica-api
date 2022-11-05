package com.logistica.api.controller;

import com.logistica.api.model.AddresseeModel;
import com.logistica.api.model.DeliveryModel;
import com.logistica.domain.model.Delivery;
import com.logistica.domain.repository.DeliveryRepository;
import com.logistica.domain.services.DeliveryService;
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
    public ResponseEntity<DeliveryModel> findDeliveryId(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(delivery -> {
                    DeliveryModel deliveryModel = new DeliveryModel();
                    deliveryModel.setId(delivery.getId());
                    deliveryModel.setClientName(delivery.getClient().getName());
                    deliveryModel.setAddresseeModel(new AddresseeModel());
                    deliveryModel.getAddresseeModel().setName(delivery.getAddressee().getName());
                    deliveryModel.getAddresseeModel().setAddress(delivery.getAddressee().getAddress());
                    deliveryModel.getAddresseeModel().setNumber(delivery.getAddressee().getNumber());
                    deliveryModel.getAddresseeModel().setComplement(delivery.getAddressee().getComplement());
                    deliveryModel.getAddresseeModel().setDistrict(delivery.getAddressee().getDistrict());
                    deliveryModel.setTax(delivery.getTax());
                    deliveryModel.setDeliveryStatus(delivery.getStatus());
                    deliveryModel.setDateOrder(delivery.getDateOrder());
                    deliveryModel.setDateFinished(delivery.getDateFinished());
                    return ResponseEntity.ok(deliveryModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}