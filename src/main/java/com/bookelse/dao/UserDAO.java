package com.bookelse.dao;

import com.bookelse.model.user.User;
import com.bookelse.model.user.UserCredentials;
import java.util.List;
import java.util.Optional;

public interface UserDAO<T extends User> {
  T registerUserInDB(T user) ;

  Optional<UserCredentials> getUserCredentials(String id) ;

  Optional<T> getUserById(String id) ;

  List<T> listAllUsers() ;

  Boolean isUserExist(String userId);
}
