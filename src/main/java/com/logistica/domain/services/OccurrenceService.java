package com.logistica.domain.services;

import com.logistica.domain.exception.DomainException;
import com.logistica.domain.model.Delivery;
import com.logistica.domain.model.Occurrence;
import com.logistica.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OccurrenceService {

    private FindDeliveryService findDeliveryService;

    @Transactional
    public Occurrence registrationOccurrence(Long deliveryId, String description) {
        Delivery delivery = findDeliveryService.findDelivery(deliveryId);
        return delivery.addOccurrence(description);
    }
}