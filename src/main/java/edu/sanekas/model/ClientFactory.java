package edu.sanekas.model;

import org.springframework.stereotype.Service;

@Service
public class ClientFactory {
    public Client createClient(String name) {
        final Client client = new Client();
        client.setName(name);
        return client;
    }
}
