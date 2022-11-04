package com.logisticaapi.controller;

import com.logisticaapi.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/clients")
    public List<Client> list() {
        return manager.createQuery("from Client", Client.class).getResultList();
    }
}