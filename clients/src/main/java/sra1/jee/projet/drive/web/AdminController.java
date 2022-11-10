package sra1.jee.projet.drive.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sra1.jee.projet.drive.model.Banished;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.Reclamation;
import sra1.jee.projet.drive.model.ReclamationStatus;
import sra1.jee.projet.drive.repository.AdminBanishedRepository;
import sra1.jee.projet.drive.repository.AdminClientRepository;
import sra1.jee.projet.drive.repository.AdminReclamationRepository;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
	
	@Autowired
	AdminReclamationRepository reclaRepo;
	
	@Autowired
	AdminClientRepository clientRepo;
	
	@Autowired
	AdminBanishedRepository banRepo;
	
	public boolean isAdmin(HttpSession session) {
	    if ((boolean) session.getAttribute("authentication") != true) return false;
        long id = (long) session.getAttribute("id_client");
        Client client = this.clientRepo.findById(id);
        
        if (client.getRole().equals("ADMIN")) return true;
        return false;
	}
	
	@GetMapping(path = "/page.html")
	public String displayAdmin(Model model, HttpServletRequest request) {	
	    if (isAdmin(request.getSession())) return "admin/admin_page";
	    return "error";
	}
	
	@GetMapping(path = "/listReclamations.html")
	public String listAllReclamations(Model model, HttpServletRequest request) {
	    if (!isAdmin(request.getSession())) return "error";
		List<Reclamation> reclamations = this.reclaRepo.findByStatusOrderByDateTimeDesc(ReclamationStatus.WAITING);
		model.addAttribute("reclamations", reclamations);
		
		return "admin/list_reclamations_admin";
	}
	
	@GetMapping(path = "/listUsers.html")
	public String listAllUsers(Model model, HttpServletRequest request) {
        if (!isAdmin(request.getSession())) return "error";
		List<Client> clients = this.clientRepo.findByOrderByLastname();
		List<Banished> banished = this.banRepo.findBy();
		
		model.addAttribute("clients", clients);
		model.addAttribute("banished", banished);
		
		return "admin/list_users_admin";
	}
	
	@RequestMapping(path = "/ban/{id}.data")
	public String banClient(@PathVariable(name = "id") long id, HttpServletRequest request) {
        if (!isAdmin(request.getSession())) return "error";
		Client toBan = this.clientRepo.findById(id);
		Banished ban = new Banished();
		ban.setClient(toBan);
		this.banRepo.save(ban);
		
		return "redirect:/admin/listUsers.html";
	}
	
	@RequestMapping(path = "/unban/{id}.data")
	public String unbanClient(@PathVariable(name = "id") long id, HttpServletRequest request) {
        if (!isAdmin(request.getSession())) return "error";
		Banished toUnban = this.banRepo.findById(id);
		this.banRepo.delete(toUnban);
		
		return "redirect:/admin/listUsers.html";
	}
}