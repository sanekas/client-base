package edu.sanekas.controllers;

import edu.sanekas.dao.ClientService;
import edu.sanekas.model.Client;
import edu.sanekas.model.ClientRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

    @ApiOperation(value = "Returns all clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No content")
    })
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<Collection<Client>> getAll() {
        final Collection<Client> clients = clientService.getAll();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        clients.forEach(cl -> cl.add(linkTo(methodOn(ClientsController.class).getClientById(cl.getDbId())).withSelfRel()));
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns client by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
        final Optional<Client> client = clientService.getById(id);
        client.ifPresent(cl -> cl.add(linkTo(methodOn(ClientsController.class).getClientById(cl.getDbId())).withSelfRel()));
        return client.map(cl -> new ResponseEntity<>(cl, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Creates new client with unique id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
    })
    @RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody ClientRequest clientRequest) {
        final Client client = clientService.create(clientRequest);
        client.add(linkTo(methodOn(ClientsController.class).getClientById(client.getDbId())).withSelfRel());
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates and returns fresh client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable("id") String id, @RequestBody ClientRequest clientRequest) {
        final Optional<Client> client = clientService.update(id, clientRequest);
        client.ifPresent(cl -> cl.add(linkTo(methodOn(ClientsController.class).getClientById(cl.getDbId())).withSelfRel()));
        return client.map(cl -> new ResponseEntity<>(cl, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Deletes client by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteById(@PathVariable("id") String id) {
        final boolean exists = clientService.exists(id);
        if (exists) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Deletes all clients")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content")
    })
    @RequestMapping(value = "/clients", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteAll() {
        clientService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
