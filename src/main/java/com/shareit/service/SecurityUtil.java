package com.shareit.service;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author dpony
 * SecurityUtil.java
 * 
 * A utility class to help faciliate security related processes.
 * Uses the query service to authenticate accounts.
 */
@RequestScoped
public class SecurityUtil {
    private final PasswordService passwordService = new DefaultPasswordService();

    @Inject
    private QueryService queryService;


    /**
     * Could be used as an easy way to encrypt passwords.
     * not currently being used.
     * @param plainText
     * @return
     */
    public String encryptText(String plainText) {
        return passwordService.encryptPassword(plainText);
    }


    /**
     * Generating the key for JWT to be used in session management.
     * @param keyString
     * @return
     */
    public Key generateKey(String keyString) {
         return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
    }

    /**
     * Called to authenticate an account. 
     * @param accountName
     * @param password
     * @return
     */
    public boolean authenticateAccount(String accountName, String password) {
        return queryService.authenticateAccount(accountName, password);

    }

    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Used to match the db password to the clear text password.
     * @param dbStoredHashedPassword
     * @param saltText
     * @param clearTextPassword
     * @return
     */
    public boolean passwordsMatch(String dbStoredHashedPassword, String saltText, String clearTextPassword) {
        ByteSource salt = ByteSource.Util.bytes(Hex.decode(saltText));
        String hashedPassword = hashAndSaltPassword(clearTextPassword, salt);
        return hashedPassword.equals(dbStoredHashedPassword);
    }

    /**
     * Hashes and creates the salt for the clear text password.
     * @param clearTextPassword
     * @return
     */
    public Map<String, String> hashPassword(String clearTextPassword) {
        ByteSource salt = getSalt();
        Map<String, String> credMap = new HashMap<>();
        credMap.put("hashedPassword", hashAndSaltPassword(clearTextPassword, salt));
        credMap.put("salt", salt.toHex());
        return credMap;


    }

    /**
     * Hashes the clear text password and the salt 2 million times using Sha512
     * @param clearTextPassword
     * @param salt
     * @return
     */
    private String hashAndSaltPassword(String clearTextPassword, ByteSource salt) {
        return new Sha512Hash(clearTextPassword, salt, 2000000).toHex();
    }

    /**
     * Random number generator for the salt.
     * @return
     */
    private ByteSource getSalt() {
        return new SecureRandomNumberGenerator().nextBytes();
    }


}

