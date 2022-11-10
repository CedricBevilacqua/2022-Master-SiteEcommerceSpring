package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.VerificationToken;

public interface TokenRepository extends CrudRepository<VerificationToken, Long>  {
	
	public VerificationToken findByToken(String token);
	
}
