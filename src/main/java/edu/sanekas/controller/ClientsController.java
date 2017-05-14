package edu.sanekas.controller;

import edu.sanekas.dao.ClientService;
import edu.sanekas.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by sanekas on 14/05/2017.
 */
@RestController
@RequestMapping("/clients")
public class ClientsController {
    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client getClientById(@PathVariable final long id) {
        return clientService.getClientById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClientById(@PathVariable final long id) {
        clientService.deleteClientById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody final Client client) {
        clientService.updateClient(client);
    }
}
