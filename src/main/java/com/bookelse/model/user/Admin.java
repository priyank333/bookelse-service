package com.bookelse.model.user;

import com.bookelse.model.common.Password;
import com.bookelse.model.id.Id;
import com.bookelse.util.datetime.ZoneId;
import java.time.ZonedDateTime;

public class Admin extends User {
  private final String userName;

  public Admin(
      Id userId,
      Password password,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn,
      ZoneId zoneId,
      String userName) {
    super(userId, password, createdOn, updatedOn, zoneId);
    this.userName = userName;
  }

  public static Admin createAdminObject(
      Id userId,
      String userName,
      Password password,
      ZonedDateTime createdOn,
      ZonedDateTime updatedOn,
      ZoneId zoneId) {
    return new Admin(userId, password, createdOn, updatedOn, zoneId, userName);
  }

  public String getUserName() {
    return userName;
  }
}
