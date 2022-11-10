package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Reclamation;
import sra1.jee.projet.drive.model.ReclamationMessage;

public interface ReclamationMessageRepository extends CrudRepository<ReclamationMessage, Integer> {

	List<ReclamationMessage> findByContent(String content);
	
	List<ReclamationMessage> findByReclamationOrderByMessageDateAsc(Reclamation reclamation);
	
}
