package sra1.jee.projet.drive.services;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.VerificationToken;
import sra1.jee.projet.drive.repository.TokenRepository;

@Component
public class MailService {
    
    @Autowired
    TokenRepository tokenRepository;
    
    @Autowired
    JavaMailSender mailSender;
    
    public void sendVerificationMail(Client savedClient, String appUrl, String pathUrl, String message, String subject) throws MessagingException {
        // On génère le token
        String token = UUID.randomUUID().toString();
        VerificationToken myToken = new VerificationToken(token , savedClient);
        tokenRepository.save(myToken);

        // On construit le mail à envoyer
        String recipientAddress = savedClient.getMail();
        String confirmationUrl = appUrl + pathUrl + ".html?token=" + token;
       
        message += "<a href=\"/localhost:8080" + confirmationUrl + "\">localhost:8080" + confirmationUrl + "</a><br><br>";  
        MimeMessage email = mailSender.createMimeMessage();
        email.setSubject(subject);
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(email, true);
        helper.setTo(recipientAddress);
        helper.setText(message , true);
        helper.setFrom("webdrivem2gl@gmail.com");

        // On envoie le mail
        mailSender.send(email);
    }

}
