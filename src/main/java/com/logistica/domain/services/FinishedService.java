package com.logistica.domain.services;

import com.logistica.domain.exception.DomainException;
import com.logistica.domain.model.Delivery;
import com.logistica.domain.model.DeliveryStatus;
import com.logistica.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinishedService {

    private FindDeliveryService findDeliveryService;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public void finished(Long deliveryId) {
        Delivery delivery = findDeliveryService.findDelivery(deliveryId);
        delivery.finishDelivery();
        deliveryRepository.save(delivery);

    }
}