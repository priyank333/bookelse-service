package com.bookelse.model.common;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Password implements Serializable {

  private final String password;
  private final String salt;

  private Password(String password, String salt) {
    this.password = password;
    this.salt = salt;
  }

  public static Password createNewPassword(String rawPassword) {
    if (isNotBlank(rawPassword)) {
      String salt = generateSalt();
      return new Password(hashPassword(rawPassword, salt), salt);
    }
    return null;
  }

  private static String generateSalt() {
    byte[] salt = new byte[16];
    SecureRandom random = new SecureRandom();
    random.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }

  private static String hashPassword(String rawPassword, String salt) {
    try {
      byte[] saltBytes = Base64.getDecoder().decode(salt);
      PBEKeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), saltBytes, 10000, 256);
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      byte[] hash = skf.generateSecret(spec).getEncoded();
      return Base64.getEncoder().encodeToString(hash);
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
      throw new RuntimeException("Error hashing password: " + e.getMessage());
    }
  }

  public static Password createExistingPasswordObject(String password, String salt) {
    return new Password(password, salt);
  }

  public static Password createExistingPasswordFromRaw(String rawPassword, String salt) {
    return new Password(hashPassword(rawPassword, salt), salt);
  }

  public Boolean comparePassword(Password password) {
    return this.password.equals(password.password);
  }

  public String getPassword() {
    return password;
  }

  public String getSalt() {
    return salt;
  }
}
