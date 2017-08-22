package edu.sanekas.dao;

import edu.sanekas.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sanekas on 14/05/2017.
 */

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
}
