package edu.sanekas.controllers;

import edu.sanekas.dao.ClientService;
import edu.sanekas.model.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/")
@Api(value = "clientbase", description = "REST API for collecting clients")
public class ClientsController {
    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Get all clients")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Collection<Client> all() {
        return clientService.getAll();
    }

    @ApiOperation(value = "Get clients by id")
    @RequestMapping(value = "/getById", params = "id", method = RequestMethod.GET)
    public Client getById(@RequestParam(name = "id") String id) {
        return clientService.getById(id);
    }

    @ApiOperation(value = "Get clients by name")
    @RequestMapping(value = "/getByName", params = "name", method = RequestMethod.GET)
    public Collection<Client> getByName(@RequestParam(name = "name") String name) {
        return clientService.getByName(name);
    }

    @ApiOperation(value = "Create new client")
    @RequestMapping(value = "/putNew", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putNew(@RequestBody Client client) {
        clientService.add(client);
    }


    @ApiOperation(value = "Update existed client")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Client client) {
        clientService.update(client);
    }

    @ApiOperation(value = "Delete client by id")
    @RequestMapping(value = "/deleteById", params = "id", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam(name = "id") String id) {
        clientService.deleteById(id);
    }

    @ApiOperation(value = "Delete client by name")
    @RequestMapping(value = "/deleteByName", params = "name", method = RequestMethod.DELETE)
    public void deleteByName(@RequestParam(name = "name") String name) {
        clientService.deleteByName(name);
    }
}
