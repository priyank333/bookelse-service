package com.bookelse.model.user;

import com.bookelse.model.common.Password;
import com.bookelse.model.id.Id;
import java.time.ZonedDateTime;

public class Admin extends User {
  private final String userName;

  public Admin(
      Id userId,
      Password password,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn,
      String userName) {
    super(userId, password, createdOn, updatedOn);
    this.userName = userName;
  }

  public static Admin createAdminObject(
      Id userId,
      String userName,
      Password password,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn) {
    return new Admin(userId, password, createdOn, updatedOn, userName);
  }

  public String getUserName() {
    return userName;
  }
}
