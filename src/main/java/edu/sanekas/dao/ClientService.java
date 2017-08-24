package edu.sanekas.dao;

import edu.sanekas.model.Client;
import edu.sanekas.model.ClientRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by sanekas on 14/05/2017.
 */
public interface ClientService {
    @NotNull
    Collection<Client> getAll();

    Optional<Client> getById(String id);

    @NotNull
    Client create(ClientRequest clientRequest);

    Optional<Client> update(String id, ClientRequest jsonClient);

    boolean exists(String id);

    void deleteById(String id);
}
