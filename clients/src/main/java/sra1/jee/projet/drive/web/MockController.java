package sra1.jee.projet.drive.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sra1.jee.projet.drive.mock.ClientMock;
import sra1.jee.projet.drive.mock.ReclamationMock;
import sra1.jee.projet.drive.model.Banished;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.Reclamation;
import sra1.jee.projet.drive.repository.AdminBanishedRepository;
import sra1.jee.projet.drive.repository.AdminClientRepository;
import sra1.jee.projet.drive.repository.AdminReclamationRepository;

@Controller
@RequestMapping(path = "/mock")
public class MockController {
    @Autowired
    AdminReclamationRepository reclaRepo;
    
    @Autowired
    AdminClientRepository clientRepo;
    
    @Autowired
    AdminBanishedRepository banRepo;

    @RequestMapping(path = "/addMocks.data")
    public String addUsersMock() {
        ClientMock mock = new ClientMock();
        Map<Long, Client> clientsMock = mock.getClients();
        Map<Long, Banished> bannedMock = mock.banned;
        
        ReclamationMock rmock = new ReclamationMock();
        Map<Integer, Reclamation> reclamationsMock = rmock.getRecls();
        
        this.clientRepo.save(clientsMock.values());
        this.reclaRepo.save(reclamationsMock.values());
        this.banRepo.save(bannedMock.values());
        return "redirect:/auth/login.html";
    }
}
