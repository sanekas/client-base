package edu.sanekas.dao;

import edu.sanekas.model.Client;
import edu.sanekas.model.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by sanekas on 14/05/2017.
 */
@Component
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientFactory clientFactory;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, ClientFactory clientFactory) {
        this.repository = repository;
        this.clientFactory = clientFactory;
    }

    @Override
    public Client getById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void deleteById(String id) {
        repository.delete(id);
    }

    @Override
    public List<Client> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public Collection<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Client client) {
        final Client c = repository.findOne(client.getId());
        if (c != null) {
            c.setName(client.getName());
        }
        repository.save(c);
    }

    @Override
    public void add(Client client) {
        repository.save(client);
    }

    @Override
    public void add(String name) {
        final Client client = clientFactory.createClient(name);
        repository.save(client);
    }
}
