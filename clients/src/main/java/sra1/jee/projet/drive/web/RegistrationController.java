package sra1.jee.projet.drive.web;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sra1.jee.projet.drive.dto.ClientEntry;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.VerificationToken;
import sra1.jee.projet.drive.repository.ClientRepository;
import sra1.jee.projet.drive.repository.TokenRepository;
import sra1.jee.projet.drive.services.MailService;

@Controller
@RequestMapping(path="/registration")
public class RegistrationController {
        
    @Autowired
    ClientRepository repoClient;
    
    @Autowired
    MailService mailService;
    
    @Autowired
    TokenRepository tokenRepository; 
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView showSignUpForm() {
        return new ModelAndView("sign_up", "client", new Client());
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createAccount(@Valid @ModelAttribute("client")ClientEntry clientEntry, 
      BindingResult result, Model model, HttpServletRequest request) throws IOException, MessagingException {
        String errorMessage = "";
        
        if( result.hasErrors() ) {
            model.addAttribute("errorMessage", "La création de votre compte a échoué, Veuillez vérifier puis corriger vos entrées");
            return "sign_up";
        }
        
        // Vérifier l'unicité du mail
        if (!(repoClient.findByMail(clientEntry.getMail()) == null)) {
            errorMessage = "un compte existe déjà pour cette adresse mail";
            model.addAttribute("errorMessage", errorMessage);
            return "sign_up";
        }

        Client client = createClientFromClientEntry(clientEntry);
        repoClient.save(client);
        
        Client savedClient = repoClient.findByMail(clientEntry.getMail());
        String appUrl = request.getContextPath();
        String pathUrl = "/registration/confirm";
        String message = "Merci de vous être inscrit au WEBDRIVE. <br><br>"
                + "Cliquez sur le lien ci-dessous ou copiez-le et collez-le dans la barre d'adresse de votre navigateur. <br><br>";
        String subject = "Activer votre compte sur WEBDRIVE";
        mailService.sendVerificationMail(savedClient, appUrl, pathUrl, message, subject);
                
        return "email_confirmation";
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration
      (WebRequest request, Model model, @RequestParam("token") String token, RedirectAttributes attrs) {
        String errorMessage = "";    
        
        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null) {
            errorMessage = "Oups ! Le lien d'activation du compte est invalide.";
            model.addAttribute("errorMessage", errorMessage);
            return "invalid_activation_link";
        }
        
        Client client = verificationToken.getClient();
        Calendar cal = Calendar.getInstance();
        
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            errorMessage = "Oups ! Le lien d'activation du compte a expiré.";
            model.addAttribute("expired", "expired");
            model.addAttribute("accountActivation", "accountActivation");
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("savedClient", client);

            return "invalid_activation_link";
        } 
        
        client.setIs_valid(true);
        repoClient.save(client);
        String activationMessage = "Votre compte est désormais activé";
        
        attrs.addFlashAttribute("activationMessage", activationMessage);
        return "redirect:/auth/login.html"; 
    }
    
    @RequestMapping(value = "/send-new-confirmation-mail", method = RequestMethod.POST)
    public String sendNewConfirmationLink(Model model, HttpServletRequest request) throws MessagingException {
        
        request.getParameter("id_client");
        Client savedClient = repoClient.findOne(Long.valueOf(request.getParameter("id_client")));
        
        String appUrl = request.getContextPath();

        String pathUrl = "/registration/confirm";
        String message = "Merci de vous être inscrit au WEBDRIVE. <br><br>"
                + "Cliquez sur le lien ci-dessous ou copiez-le et collez-le dans la barre d'adresse de votre navigateur. <br><br>";
        String subject = "Activer votre compte sur WEBDRIVE";
        mailService.sendVerificationMail(savedClient, appUrl, pathUrl, message, subject);
        return "email_confirmation";
        
    }

    private Client createClientFromClientEntry(ClientEntry clientEntry) {
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();        
        String hashedPassword = passwordEncoder.encode(clientEntry.getPassword());

        Client client = new Client();
        client.setFirstname(clientEntry.getFirstname());
        client.setLastname(clientEntry.getLastname());
        client.setMail(clientEntry.getMail());
        client.setPassword(hashedPassword);
        client.setNumberHouse(clientEntry.getNumberHouse());
        client.setStreet(clientEntry.getStreet());
        client.setZipCode(clientEntry.getZipCode());
        client.setTown(clientEntry.getTown());
        client.setCountry(clientEntry.getCountry());
        return client;
    }
    
}
