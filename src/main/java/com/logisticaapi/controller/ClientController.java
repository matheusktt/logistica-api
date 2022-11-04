package com.logisticaapi.controller;

import com.logisticaapi.domain.model.Client;
import com.logisticaapi.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public List<Client> list() {
//        return clientRepository.findByName("Matheus");
        return clientRepository.findAll();
    }
}