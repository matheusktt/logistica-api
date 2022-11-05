package com.logisticaapi.domain.services;

import com.logisticaapi.domain.model.Client;
import com.logisticaapi.domain.model.Delivery;
import com.logisticaapi.domain.model.DeliveryStatus;
import com.logisticaapi.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class DeliveryService {

    private ClientService clientService;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery requestDelivery(Delivery delivery) {

        Client client = clientService.findClient(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setDateOrder(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }
}