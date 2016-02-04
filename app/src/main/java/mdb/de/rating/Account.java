package mdb.de.rating;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by a556794 on 11.12.2015.
 */
public class Account {

    public String email;
    public String password;
    public String passwordValidation;
    public String username;
    public String passwordSalt;

    public Account(String email, String password, String passwordValidation, String username){
        this.email = email;
        this.password = password;
        this.passwordValidation = passwordValidation;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordValidation() {
        return passwordValidation;
    }

    public void setPasswordValidation(String passwordValidation) {
        this.passwordValidation = passwordValidation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public static SecretKey generatePasswordSalt(String password) throws NoSuchAlgorithmException {
        final int outputKeyLength = 256;
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(outputKeyLength, secureRandom);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

   /* public static PasswordAuthentication passwordAuthentication (String password, String passwordValidation){
        return new PasswordAuthentication(username, new char[{78}]);
    }*/
}
