package sra1.jee.projet.drive.web;

import java.util.Calendar;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sra1.jee.projet.drive.dto.ClientEntry;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.VerificationToken;
import sra1.jee.projet.drive.repository.ClientRepository;
import sra1.jee.projet.drive.repository.TokenRepository;
import sra1.jee.projet.drive.services.MailService;



@Controller
@RequestMapping(path="/account")
public class ClientController {
    
    @Autowired
    ClientRepository repoClient;
    
    @Autowired
    TokenRepository tokenRepository;    
    
    @Autowired
    MailService mailService;
    
    @GetMapping(path="/edit")
    public String updateClient(Model model, HttpSession session) {
        if (session.getAttribute("id_client") == null) {
            return "redirect:/auth/login.html";
        }
        Client client = repoClient.findOne((Long) session.getAttribute("id_client"));
        model.addAttribute("clientToUpdate", client);
        return "edit_account";
    }

    @PostMapping(path="/edit")
    public String updateClient(@Valid @ModelAttribute("clientToUpdate")ClientEntry clientEntry, 
        BindingResult result, Model model, HttpSession session, HttpServletRequest request) throws MessagingException {

        if( result.hasErrors() ) {
            model.addAttribute("errorMessage", "La mise à jour de votre compte a échoué, Veuillez vérifier puis corriger vos entrées");
            return "edit_account";
        }
        Client clientToUpdate = repoClient.findOne((Long) session.getAttribute("id_client"));
        if (!clientToUpdate.getMail().contentEquals(clientEntry.getMail())) {
            setUpdatedValues(clientEntry, clientToUpdate, false);
            repoClient.save(clientToUpdate);
            
            if (repoClient.findByMail(clientEntry.getMail()) != null){
                model.addAttribute("errorMessage", "un compte existe déjà pour cette adresse mail");
                return "edit_account";
            }
            
            // On crée un client temporaire avec les nouvelles données
            Client tempClient = new Client();
            setUpdatedValues(clientEntry, tempClient, true);
            repoClient.save(tempClient);
            
            String appUrl = request.getContextPath();
            String pathUrl = "/account/confirm-mail-update";
            // on envoie un token lié à l'utilisateur temporaire
            String message = "Vous avez modifié votre adresse mail <br><br>"
                    + "Cliquez sur le lien ci-dessous ou copiez-le et collez-le dans la barre d'adresse de votre navigateur. <br><br>";
            String subject = "Confirmez votre nouvelle adresse mail";
            mailService.sendVerificationMail(tempClient, appUrl, pathUrl, message, subject);
            
            return "new_mail_confirmation";
        }
        
        setUpdatedValues(clientEntry, clientToUpdate, true);
        repoClient.save(clientToUpdate);
        
        model.addAttribute("successMessage", "Votre compte a été mis à jour avec succès");
        
        return "edit_account";
    }
    
    @RequestMapping(value = "/confirm-mail-update", method = RequestMethod.GET)
    public String confirmMailUpdate(HttpSession session, WebRequest request, Model model, @RequestParam("token") String token, RedirectAttributes attrs) {
        String errorMessage = "";    

        VerificationToken verificationToken = tokenRepository.findByToken(token);
        System.out.println("VERIFICATIONTOKEN" + verificationToken);
        if (verificationToken == null) {
            errorMessage = "Oups ! Le lien de confirmation de l'adresse mail est invalide.";
            model.addAttribute("errorMessage", errorMessage);
            return "invalid_activation_link";
        }
        
        
        Client tempClient = verificationToken.getClient();
        Calendar cal = Calendar.getInstance();
        
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            errorMessage = "Oups ! Le lien de confirmation de l'adresse mail a expiré.";
            model.addAttribute("expired", "expired");
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("savedClient", tempClient);

            return "invalid_activation_link";
        } 
        
        if (session.getAttribute("id_client") == null) {
            model.addAttribute("errorMessage", "Cette opération nécessite votre connection !");
            return "invalid_activation_link";
        }
        
        // On récupère le client connecté pour mettre à jour son compte
        System.out.println("ID SESSION" + session.getAttribute("id_client"));
        Client clientToUpdate = repoClient.findOne((Long) session.getAttribute("id_client"));
        setClientValues(tempClient , clientToUpdate);
        
        // On détruit le token et le client temporaire
        tokenRepository.delete(verificationToken);
        repoClient.delete(tempClient);
        
        // On met à jour le client
        repoClient.save(clientToUpdate);
        
        attrs.addFlashAttribute("mailUpdate", "Votre adresse mail a été mise à jour avec succès");
        return "redirect:/account/edit.html";
        
    }
    
    private void setUpdatedValues(ClientEntry clientEntry, Client clientToUpdate, boolean includeMail) {
        clientToUpdate.setFirstname(clientEntry.getFirstname());
        clientToUpdate.setLastname(clientEntry.getLastname());
        if (includeMail) {
            clientToUpdate.setMail(clientEntry.getMail());
        }
        clientToUpdate.setNumberHouse(clientEntry.getNumberHouse());
        clientToUpdate.setStreet(clientEntry.getStreet());
        clientToUpdate.setTown(clientEntry.getTown());
        clientToUpdate.setZipCode(clientEntry.getZipCode());
        clientToUpdate.setCountry(clientEntry.getCountry());
    }
    
    private void setClientValues(Client client, Client clientToUpdate) {
        clientToUpdate.setFirstname(client.getFirstname());
        clientToUpdate.setLastname(client.getLastname());
        clientToUpdate.setMail(client.getMail());
        clientToUpdate.setNumberHouse(client.getNumberHouse());
        clientToUpdate.setStreet(client.getStreet());
        clientToUpdate.setTown(client.getTown());
        clientToUpdate.setZipCode(client.getZipCode());
        clientToUpdate.setCountry(client.getCountry());
    }


}
