package com.logistica.domain.services;

import com.logistica.domain.exception.DomainException;
import com.logistica.domain.exception.EntityNotFoundException;
import com.logistica.domain.model.Delivery;
import com.logistica.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindDeliveryService {

    private DeliveryRepository deliveryRepository;

    public Delivery findDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found."));
    }
}