package edu.sanekas.dao;

import edu.sanekas.model.Client;
import edu.sanekas.model.ClientRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

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

    @NotNull
    @Override
    public Collection<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> getById(String id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @NotNull
    @Override
    public Client create(ClientRequest clientRequest) {
        return repository.save(clientRequest);
    }

    @Override
    public Optional<Client> update(String id, ClientRequest clientRequest) {
        final Optional<Client> client = Optional.ofNullable(repository.findOne(id));
        client.ifPresent(cl -> repository.save(clientRequest));
        return client;
    }

    @Override
    public boolean exists(String id) {
        return repository.exists(id);
    }

    @Override
    public void deleteById(String id) {
        repository.delete(id);
    }
}
