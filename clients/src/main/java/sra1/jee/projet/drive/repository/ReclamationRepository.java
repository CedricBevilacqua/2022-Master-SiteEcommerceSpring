package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Reclamation;
import sra1.jee.projet.drive.model.ReclamationStatus;

public interface ReclamationRepository extends CrudRepository<Reclamation, Integer> {
    List<Reclamation> findByIdClientOrderByDateTimeDesc(long idClient);
}
