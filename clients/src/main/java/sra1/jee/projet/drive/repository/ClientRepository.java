package sra1.jee.projet.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sra1.jee.projet.drive.model.Client;


public interface ClientRepository extends CrudRepository<Client, Long> {
    
    Client findByMail(String mail);


}
