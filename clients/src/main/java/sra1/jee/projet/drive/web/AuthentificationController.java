package sra1.jee.projet.drive.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sra1.jee.projet.drive.dto.ClientEntry;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.repository.BanishedRepository;
import sra1.jee.projet.drive.repository.ClientRepository;
import sra1.jee.projet.drive.services.AuthentificationService;


@Controller
@RequestMapping(path="/auth")
public class AuthentificationController {
    
    @Autowired
    ClientRepository repoClient;
 
    @Autowired
    BanishedRepository repoBanished;
   
    @Autowired
    AuthentificationService authService;
    

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm() {
        return new ModelAndView("login", "loggingClient", new Client());
    }
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @Valid @ModelAttribute("loggingClient")ClientEntry clientEntry, 
      BindingResult result, Model model) {
        String errorMessage = "";

        // l'utilisateur n'a pas un compte
        if (repoClient.findByMail(clientEntry.getMail()) == null) {
        	errorMessage = "L'adresse mail est incorrecte";
            model.addAttribute("errorMessage", errorMessage);
        }
        
        // l'utilisateur est banni
        else if (repoBanished.findByClient((repoClient.findByMail(clientEntry.getMail()))) != null)   
        {
        	errorMessage = "Impossible de se connecter";
            model.addAttribute("errorMessage", errorMessage);
        }
        
        // Le mot de passe est incorrect
        else if (!authService.verifyPassword(clientEntry.getMail(), clientEntry.getPassword())) {
        	errorMessage = "Le mot de passe est incorrect";
            model.addAttribute("errorMessage", errorMessage);       	
        }
        
        else {
			HttpSession session = request.getSession();
			session.setAttribute("authentication", true);
			Client client = repoClient.findByMail(clientEntry.getMail());
			Long idClient = client.getId();
			session.setAttribute("role_client", client.getRole());
			session.setAttribute("id_client", idClient);
			session.setAttribute("valid_account", client.isValid());
			System.out.println(session.getAttribute("valid_account"));
            return "home";
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET) 
    	public ModelAndView logout(HttpServletRequest request) {
    		HttpSession session = request.getSession();
    		if ((boolean) session.getAttribute("authentication")) {
    			session.setAttribute("authentication", false);
    			session.removeAttribute("id_client");
    			session.removeAttribute("role_client");
    			session.removeAttribute("valid_account");
    		}
	        return new ModelAndView("login", "loggingClient", new Client());
    	}
    
}
