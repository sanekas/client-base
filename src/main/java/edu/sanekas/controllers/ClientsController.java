package edu.sanekas.controllers;

import edu.sanekas.dao.ClientService;
import edu.sanekas.model.Client;
import edu.sanekas.model.JsonClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by sanekas on 14/05/2017.
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "clientbase", description = "REST API for collecting clients")
public class ClientsController {
    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Get all clients")
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<Collection<Client>> getAll() {
        final Collection<Client> clients = clientService.getAll();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns client by id")
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
        final Optional<Client> client = clientService.getById(id);
        return client.map(cl -> new ResponseEntity<>(cl, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Creates new client with unique id")
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Client> createClient(@RequestBody JsonClient jsonClient) {
        final Client client = clientService.create(jsonClient);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates and returns fresh client")
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable("id") String id, @RequestBody JsonClient jsonClient) {
        final Optional<Client> client = clientService.update(id, jsonClient);
        return client.map(cl -> new ResponseEntity<>(cl, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Deletes client by id")
    @RequestMapping(value = "/deleteById", params = "id", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteById(@RequestParam(name = "id") String id) {
        final boolean exists = clientService.exists(id);
        if (exists) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
