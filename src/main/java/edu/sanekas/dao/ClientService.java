package edu.sanekas.dao;

import edu.sanekas.model.Client;
import edu.sanekas.model.JsonClient;
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
    Client create(JsonClient jsonClient);

    Optional<Client> update(String id, JsonClient jsonClient);

    boolean exists(String id);

    void deleteById(String id);
}
