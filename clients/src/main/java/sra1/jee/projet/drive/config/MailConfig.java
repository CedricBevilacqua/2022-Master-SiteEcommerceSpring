package sra1.jee.projet.drive.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	static final String APIKEY = "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";

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.sendgrid.net");
        mailSender.setPort(587);

        mailSender.setUsername("apikey");
        mailSender.setPassword(APIKEY);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        return mailSender;
    }

}
