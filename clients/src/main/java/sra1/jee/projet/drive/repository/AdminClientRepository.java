package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Client;

public interface AdminClientRepository extends CrudRepository<Client, Long>{
	List<Client> findByOrderByLastname();
	
	Client findById(long id);
}
