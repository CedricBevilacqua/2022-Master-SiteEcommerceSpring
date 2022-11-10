package sra1.jee.projet.drive.repository;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Banished;
import sra1.jee.projet.drive.model.Client;

public interface BanishedRepository extends CrudRepository<Banished, Long> {
	
    Banished findByClient(Client client);

}
