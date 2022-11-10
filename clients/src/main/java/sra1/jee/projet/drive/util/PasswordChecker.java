package sra1.jee.projet.drive.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PasswordChecker {
    
    public String encode(String mail, String password) {
        // encode password with login with HMAC-SHA1
        Mac mac;
        byte [] encodedPwd = null;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(mail.getBytes(), "HmacSHA1"));
            encodedPwd = mac.doFinal(password.getBytes());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No Algorithm found for the Mac");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("InValidKey");
        }
        return Base64.getEncoder().encodeToString(encodedPwd);
    }

}
