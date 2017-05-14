package edu.sanekas.dao;

import edu.sanekas.model.Client;

import java.util.Collection;

/**
 * Created by sanekas on 14/05/2017.
 */
public interface ClientService {
    Client getClientById(final long id);
    Collection<Client> getAllClients();
    void deleteClientById(final long id);
    void updateClient(final Client client);
}
