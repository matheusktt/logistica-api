package com.logistica.domain.services;

import com.logistica.domain.exception.DomainException;
import com.logistica.domain.model.Client;
import com.logistica.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientService {

    private ClientRepository clientRepository;

    public Client findClient(Long clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new DomainException("Client not found"));
    }

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
