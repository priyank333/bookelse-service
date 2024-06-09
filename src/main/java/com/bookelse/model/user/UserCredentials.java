package com.bookelse.model.user;

import java.util.Objects;

public class UserCredentials {
  private final String id;
  private final String password;
  private final String salt;

  public UserCredentials(String id, String password, String salt) {
    this.id = id;
    this.password = password;
    this.salt = salt;
  }

  public String getPassword() {
    return password;
  }

  public String getSalt() {
    return salt;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    UserCredentials that = (UserCredentials) object;
    return Objects.equals(getId(), that.getId());
  }

  public String getId() {
    return id;
  }
}
