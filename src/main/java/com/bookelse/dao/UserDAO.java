package com.bookelse.dao;

import com.bookelse.model.user.User;
import com.bookelse.model.user.UserCredentials;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO<T extends User> {
  T registerUserInDB(T user) throws SQLException;

  Optional<UserCredentials> getUserCredentials(String id) throws SQLException;

  Optional<T> getUserById(String id) throws SQLException;

  List<T> listAllUsers() throws SQLException;

  Boolean isUserExist(String userId);
}
