package sra1.jee.projet.drive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sra1.jee.projet.drive.repository.ClientRepository;
import sra1.jee.projet.drive.util.PasswordChecker;

@Component
public class AuthentificationService {
	
	@Autowired
	ClientRepository repoClient;
	
	@Autowired
	PasswordChecker pwdChecker;

	public boolean verifyPassword(String mail, String password) {
	    
		String savedPassword = repoClient.findByMail(mail).getPassword();		
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
        
		return passwordEncoder.matches(password, savedPassword);
	}
}
