package edu.sanekas.dao;

import edu.sanekas.model.Client;
import edu.sanekas.model.ClientBuilder;
import edu.sanekas.model.JsonClient;
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
    private final ClientBuilder clientBuilder;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, ClientBuilder clientBuilder) {
        this.repository = repository;
        this.clientBuilder = clientBuilder;
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
    public Client create(JsonClient jsonClient) {
        final Client client = clientBuilder.buildClient(jsonClient);
        repository.save(client);
        return client;
    }

    @Override
    public Optional<Client> update(String id, JsonClient jsonClient) {
        final Optional<Client> client = Optional.ofNullable(repository.findOne(id));
        client.ifPresent(cl -> {
            clientBuilder.updateClient(cl, jsonClient);
            repository.save(cl);
        });
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
