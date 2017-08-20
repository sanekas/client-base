package edu.sanekas.dao;

import edu.sanekas.model.Client;

import java.util.Collection;

/**
 * Created by sanekas on 14/05/2017.
 */
public interface ClientService {
    Client getById(String id);
    void deleteById(String id);

    Collection<Client> getByName(String name);
    void deleteByName(String name);

    Collection<Client> getAll();

    void update(Client client);
    void add(Client client);
    void add(String name);
}
