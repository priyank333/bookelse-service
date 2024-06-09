package com.bookelse.service;

import com.bookelse.model.user.User;
import java.util.List;

public interface UserService<T extends User> {
  T registerUser(T user);

  List<T> listAllUsers();

  Boolean validateUserCredentials(String id, String password);

  T getUserById(String id);

  Boolean isUserExist(String id);
}
