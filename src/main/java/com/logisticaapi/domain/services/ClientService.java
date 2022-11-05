package com.logisticaapi.domain.services;

import com.logisticaapi.domain.exception.DomainException;
import com.logisticaapi.domain.model.Client;
import com.logisticaapi.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client) {

        boolean emailDuplicate = clientRepository.findByEmail(client.getEmail())
                .stream()
                .anyMatch(clientExisting -> !clientExisting.equals(client));

        if (emailDuplicate) {
            throw new DomainException("Email duplicate.");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
