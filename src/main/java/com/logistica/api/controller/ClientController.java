package com.logistica.api.controller;

import com.logistica.domain.model.Client;
import com.logistica.domain.repository.ClientRepository;
import com.logistica.domain.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientRepository clientRepository;
    private ClientService clientService;

    @GetMapping
    public List<Client> list() {
//        return clientRepository.findByName("Matheus");
        return clientRepository.findAll();
    }
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> find(@PathVariable Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> ResponseEntity.ok(client)).orElse(ResponseEntity.notFound().build());
//              .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

//        Optional<Client> client = clientRepository.findById(clientId);
//        if (client.isPresent()) {
//            return ResponseEntity.ok(client.get());
//        }
//        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@Valid @RequestBody Client client) {
//        return clientRepository.save(client);
        return clientService.saveClient(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@Valid @PathVariable Long clientId, @RequestBody Client client) {
        if (!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
//        client = clientRepository.save(client);
        client = clientService.saveClient(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> removeClient(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }

//        clientRepository.deleteById(clientId);
        clientService.deleteClient(clientId);

        return ResponseEntity.noContent().build();
    }
}