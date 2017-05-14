package edu.sanekas.dao;

import edu.sanekas.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sanekas on 14/05/2017.
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
