package com.logisticaapi.controller;

import com.logisticaapi.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clients")
    public List<Client> list() {
        Client client = new Client();
        client.setId(1L);
        client.setNome("Matheus");
        client.setEmail("matheus@gmail.com");
        client.setTelefone("11 91122-3344");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setNome("Pedro");
        client2.setEmail("pedro@gmail.com");
        client2.setTelefone("11 92211-4433");

        return Arrays.asList(client, client2);
    }
}