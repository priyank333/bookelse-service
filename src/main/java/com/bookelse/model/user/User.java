package com.bookelse.model.user;

import com.bookelse.model.common.Password;
import com.bookelse.model.id.Id;
import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class User {
  protected final Id userId;
  protected final Password password;
  protected final ZonedDateTime createdOn;
  protected final ZonedDateTime updatedOn;

  public User(
      Id userId,
      Password password,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    this.userId = userId;
    this.password = password;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
  }

  public Boolean validatePassword(Password password) {
    return this.password.comparePassword(password);
  }

  public Password getPassword() {
    return password;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId());
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    User user = (User) object;
    return Objects.equals(getUserId(), user.getUserId());
  }

  public Id getUserId() {
    return userId;
  }

  public ZonedDateTime getCreatedOn() {
    return createdOn;
  }

  public ZonedDateTime getUpdatedOn() {
    return updatedOn;
  }
}
