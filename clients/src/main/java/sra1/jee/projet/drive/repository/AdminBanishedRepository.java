package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Banished;

public interface AdminBanishedRepository extends CrudRepository<Banished, Long> {
	List<Banished> findBy();
	
	Banished findById(long id);
}
