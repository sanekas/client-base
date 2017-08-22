package edu.sanekas.model;

import org.springframework.stereotype.Component;

@Component
public class ClientBuilder {
    public Client buildClient(JsonClient jsonClient) {
        final Client client = new Client();
        client.setName(jsonClient.getName());
        return client;
    }

    public void updateClient(Client client, JsonClient jsonClient) {
        client.setName(jsonClient.getName());
    }
}
