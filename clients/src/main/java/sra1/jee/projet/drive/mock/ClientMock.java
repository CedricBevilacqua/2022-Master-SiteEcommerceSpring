package sra1.jee.projet.drive.mock;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sra1.jee.projet.drive.model.Banished;
import sra1.jee.projet.drive.model.Client;

@Component
@Qualifier("mock")
public class ClientMock {
	public HashMap<Long, Client> clients = new HashMap<Long, Client>();
	public HashMap<Long, Banished> banned = new HashMap<Long, Banished>();
	int id = 1;
	
	public ClientMock() {
		{
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
            String hashedPassword = passwordEncoder.encode("republiquecestmoi");
			Client c1 = new Client();
			c1.setFirstname("Jean-Jacques");
			c1.setLastname("Debuchon");
			c1.setMail("jjdebuchon@gmail.com");
			c1.setNumberHouse(22);
			c1.setPassword(hashedPassword);
			c1.setStreet("rue des Prés");
			c1.setCountry("France");
			c1.setZipCode("75000");
			c1.setTown("Paris");
			c1.setId(id);
			c1.setIs_valid(true);
			clients.put(c1.getId(), c1);
			
			Banished b1 = new Banished();
			b1.setId(id);
			b1.setClient(c1);
			banned.put(b1.getId(), b1);
			id++;
		}
		
		{
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
            String hashedPassword = passwordEncoder.encode("carabistouilles");
			Client c2 = new Client();
			c2.setFirstname("Manu");
			c2.setLastname("Mitron");
			c2.setMail("projeeeeet@elysee.fr");
			c2.setNumberHouse(1);
			c2.setPassword(hashedPassword);
			c2.setStreet("Elysee");
			c2.setCountry("France");
			c2.setZipCode("75000");
			c2.setTown("Paris");
			c2.setId(id);
			c2.setIs_valid(true);
			clients.put(c2.getId(), c2);
			id++;
		}
		
		{
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
            String hashedPassword = passwordEncoder.encode("5millions");
			Client c3 = new Client();
			c3.setFirstname("Virginie");
			c3.setLastname("Paprasse");
			c3.setMail("debout@gmail.com");
			c3.setNumberHouse(42);
			c3.setPassword(hashedPassword);
			c3.setStreet("rue des dettes");
			c3.setCountry("France");
			c3.setZipCode("75000");
			c3.setTown("Paris");
			c3.setId(id);
			c3.setIs_valid(true);
			clients.put(c3.getId(), c3);
			id++;
		}
		
		{
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
	        String hashedPassword = passwordEncoder.encode("Turlututu59");
            Client c4 = new Client();
            c4.setFirstname("Cyril");
            c4.setLastname("Guffroy");
            c4.setMail("cyrilguffroy.elv@gmail.com");
            c4.setNumberHouse(42);
            c4.setPassword(hashedPassword);
            c4.setStreet("truc");
            c4.setCountry("France");
            c4.setZipCode("75000");
            c4.setTown("Paris");
            c4.setId(id);
            c4.setRole("ADMIN");
            c4.setIs_valid(true);
            clients.put(c4.getId(), c4);
            id++;
        }
		
		{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
            String hashedPassword = passwordEncoder.encode("Leugeu59");
            Client c5 = new Client();
            c5.setFirstname("Pierre");
            c5.setLastname("Zebi");
            c5.setMail("pierrot@zebi.com");
            c5.setNumberHouse(42);
            c5.setPassword(hashedPassword);
            c5.setStreet("truc");
            c5.setCountry("France");
            c5.setZipCode("75000");
            c5.setTown("Paris");
            c5.setId(id);
            c5.setRole("ADMIN");
            c5.setIs_valid(false);
            clients.put(c5.getId(), c5);
            id++;
        }
	}
	
	public HashMap<Long, Client> getClients() {
		return this.clients;
	}
}