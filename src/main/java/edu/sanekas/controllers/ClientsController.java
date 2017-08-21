package edu.sanekas.controllers;

import edu.sanekas.dao.ClientService;
import edu.sanekas.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Collection<Client> getAll() {
        return clientService.getAll();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Client getById(@RequestParam(name = "id") String id) {
        return clientService.getById(id);
    }

    @RequestMapping(params = "name", method = RequestMethod.GET)
    public Collection<Client> getByName(@RequestParam(name = "name") String name) {
        return clientService.getByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Client client) {
        clientService.add(client);
    }

    @RequestMapping(value = "/add", params = "name", method = RequestMethod.PUT)
    public void add(@RequestParam(name = "name") String name) {
        clientService.add(name);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Client client) {
        clientService.update(client);
    }

    @RequestMapping(params = "id", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam(name = "id") String id) {
        clientService.deleteById(id);
    }

    @RequestMapping(params = "name", method = RequestMethod.DELETE)
    public void deleteByName(@RequestParam(name = "name") String name) {
        clientService.deleteByName(name);
    }
}
