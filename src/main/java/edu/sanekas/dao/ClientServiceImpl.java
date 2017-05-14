package edu.sanekas.dao;

import edu.sanekas.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by sanekas on 14/05/2017.
 */
@Component
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client getClientById(long id) {
        return repository.findOne(id);
    }

    @Override
    public void deleteClientById(long id) {
        repository.delete(id);
    }

    @Override
    public void updateClient(Client client) {
        repository.save(client);
    }

    @Override
    public Collection<Client> getAllClients() {
        return repository.findAll();
    }
}
