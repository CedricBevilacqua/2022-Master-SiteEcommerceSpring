package sra1.jee.projet.drive.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sra1.jee.projet.drive.dto.ReclamationEntry;
import sra1.jee.projet.drive.model.Banished;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.Order;
import sra1.jee.projet.drive.model.Reclamation;
import sra1.jee.projet.drive.model.ReclamationMessage;
import sra1.jee.projet.drive.model.ReclamationStatus;
import sra1.jee.projet.drive.repository.ClientRepository;
import sra1.jee.projet.drive.repository.OrderRepository;
import sra1.jee.projet.drive.repository.ReclamationMessageRepository;
import sra1.jee.projet.drive.repository.ReclamationRepository;

@Controller
@RequestMapping(path = "/reclamation")
public class ReclamationController {

    @Autowired
    private ReclamationRepository reclamationRepo;
    
    @Autowired
    private ReclamationMessageRepository reclamationMessageRepo;
    
    @Autowired
    private OrderRepository orderRepo;
    
    @Autowired
    private ClientRepository clientRepo;
    
    @RequestMapping(path = "/create.html", method = RequestMethod.GET)
    public String newReclamationPage(Model model, HttpSession session) {
        
        // Vérifie que le client est connecté et récupère son id
        if ((boolean) session.getAttribute("authentication") != true) {
            return "error";
        }
        
        long clientId = (long) session.getAttribute("id_client");
        
        Iterable<Order> orders = orderRepo.findByClientId(clientId);
        model.addAttribute("orders", orders);
        
        return "reclamation/creation_reclamation";
    }
    
    @GetMapping(path="/{id}", produces="text/html")
    public String reclamationPage(@PathVariable(name="id") int reclamationId, Model model, HttpServletRequest request) {
        
        // Vérifie que le client est connecté et récupère son id
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("authentication") != true) {
            return "error";
        }
        long clientId = (long) session.getAttribute("id_client");
        
        // Regarde si la réclamation existe
        Reclamation reclamation = reclamationRepo.findOne(reclamationId);
        if(reclamation != null) {
            List<ReclamationMessage> messages = reclamationMessageRepo.findByReclamationOrderByMessageDateAsc(reclamation);
            
            messages = reclamationMessageRepo.findByReclamationOrderByMessageDateAsc(reclamation);
            Iterable<Order> orders = orderRepo.findByClientId(clientId);
            // Cette boucle sert à enlever la commande de la réclamation de la liste de toute les commandes du client
            // pour pouvoir les différencier dans l'affichage de la jsp.
            for (Iterator<Order> i = orders.iterator(); i.hasNext(); ) {
                Order order = i.next();
                if(reclamation.getOrderId() != null && reclamation.getOrderId().equals(order.getId())) {
                    i.remove();
                }
            }
            
            model.addAttribute("orders", orders);
            model.addAttribute("reclamation", reclamation);
            model.addAttribute("messages", messages);
        } else {
            return "login";
        }
        
        return "reclamation/reclamation";
    }
    
    @PostMapping(path="/create.html")
    public String createReclamation(Model model, @Valid @ModelAttribute("reclamation") ReclamationEntry reclamationEntry,
            HttpServletRequest request) {
        
        // Vérifie que le client est connecté et récupère son id
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("authentication") != true) {
            return "error";
        }
        long clientId = (long) session.getAttribute("id_client");
        
        // Créer une réclamation et la stocke en base
        Reclamation reclamation = new Reclamation();
        reclamation.setDateTime(new Date());
        reclamation.setDescription(reclamationEntry.getDescription());
        reclamation.setIdClient(clientId);
        reclamation.setStatus(ReclamationStatus.WAITING);
        reclamation.setTitle(reclamationEntry.getTitle());
        Integer orderId = null;
        orderId = reclamationEntry.getOrderId();
        if(orderId != null) {
            Order order = orderRepo.findOne(orderId);
            if(order != null) {
                reclamation.setOrderId(orderId);
            } else {
                return "redirect:/auth/login";
            }
        }
        reclamationRepo.save(reclamation);
        
        return "redirect:/reclamation/page.html";
    }
    
    @PostMapping(path="/{id}", produces="text/html")
    public String updateReclamation(Model model, @PathVariable(name="id") int reclamationId,  
            @Valid @ModelAttribute("reclamation") ReclamationEntry reclamationEntry,
            HttpServletRequest request) {
        
        // Vérifie que le client est connecté
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("authentication")!= true) {
            return "error";
        }
    
        // Vérifie que la réclamation à update existe
        Reclamation reclamation = reclamationRepo.findOne(reclamationId);
        if(reclamation != null && reclamation.getStatus() != ReclamationStatus.CLOSED) {
            
            // Modifie la réclamation en base
            reclamation.setTitle(reclamationEntry.getTitle());
            reclamation.setDescription(reclamationEntry.getDescription());
            Integer orderId = reclamationEntry.getOrderId();
            if(orderId != null) {
                Order order = orderRepo.findOne(orderId);
                if(order == null) {
                    return "error";
                }
            }
            reclamation.setOrderId(orderId);
            reclamationRepo.save(reclamation);
            
        } else {
            // La réclamation n'existe pas -> erreur
            return "error";
        }
        
        // Renvoie la jsp de reclamation qui pointe sur cette réclamation modifiée
        return reclamationPage(reclamationId, model, request);
    }
    
    @PostMapping(path="/{id}/message", produces="text/html")
    public String newMessageForReclamation(Model model, @PathVariable(name="id") int reclamationId, String message,
            HttpServletRequest request) {
        
        // Vérifie que le client est connecté et récupère son id
        HttpSession session = request.getSession();
        if ((boolean)session.getAttribute("authentication") != true) {
            return "error";
        }
        long clientId = (long) session.getAttribute("id_client");
    
        // Vérifie que la réclamation à laquelle ajouter un message existe
        Reclamation reclamation = reclamationRepo.findOne(reclamationId);
        if(reclamation != null && reclamation.getStatus() != ReclamationStatus.CLOSED) {
            
            // Créer un nouveau message et l'ajoute en base pour la réclamation
            ReclamationMessage reclamationMessage = new ReclamationMessage();
            Client author = clientRepo.findOne(clientId);
            if(author == null) {
                return "error";
            }
            reclamationMessage.setCustomer(author);
            reclamationMessage.setContent(message);
            reclamationMessage.setReclamation(reclamation);
            reclamationMessage.setMessageDate(new Date());
            reclamationMessageRepo.save(reclamationMessage);
            
        } else {
            // La réclamation n'existe pas -> erreur
            return "error";
        }
        
        // Renvoie la jsp de reclamation qui pointe sur cette réclamation modifiée
        // Il faut faire un redirect, sinon l'url du client devient incohérente avec ce qui lui est affiché.
        String result = "redirect:/reclamation/" + reclamationId + ".html"; 
        return result;
    }
    
    @GetMapping(path = "/listReclamations/{id}.html")
    public String listAllReclamationsUser(@PathVariable(name = "id") long id, Model model) {
        List<Reclamation> reclamations = this.reclamationRepo.findByIdClientOrderByDateTimeDesc(id);
        model.addAttribute("reclamationsUser", reclamations);
        
        return "reclamation/my_reclamations";
    }
    
    @GetMapping(path = "/page.html")
    public String displayAdmin(Model model) {   
        return "reclamation/reclamation_index";
    }
    
    @RequestMapping(path = "/{id}/close.data")
    public String closeReclamation(@PathVariable(name = "id") int id) {
        Reclamation recl = this.reclamationRepo.findOne(id);
        recl.setStatus(ReclamationStatus.CLOSED);
        this.reclamationRepo.save(recl);
        
        return "redirect:/reclamation/{id}.html";
    }

    
}
