package com.bookelse.service;

import com.bookelse.dao.CustomerDAO;
import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.exceptions.UserNotFoundException;
import com.bookelse.model.common.Password;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.user.Customer;
import com.bookelse.model.user.UserCredentials;
import com.bookelse.payload.customer.CustomerBankAccountUpdateRequestPayload;
import com.bookelse.payload.customer.CustomerPostalAddressUpdateRequestPayload;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CustomerService")
public class CustomerService implements UserService<Customer> {
  private final CustomerDAO customerDAO;

  public CustomerService(@Qualifier("CustomerDAO") CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  @Override
  public Customer registerUser(Customer customer) {
    try {
      return customerDAO.registerUserInDB(customer);
    } catch (SQLException e) {
      throw new RuntimeExceptionAuditable(e);
    }
  }

  @Override
  public List<Customer> listAllUsers() {
    try {
      return customerDAO.listAllUsers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Boolean validateUserCredentials(String id, String password) {
    Optional<UserCredentials> userCredentialsOptional;
    try {
      userCredentialsOptional = customerDAO.getUserCredentials(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    if (userCredentialsOptional.isPresent()) {
      Password existingPassword =
          Password.createExistingPasswordObject(
              userCredentialsOptional.get().getPassword(), userCredentialsOptional.get().getSalt());
      return existingPassword.comparePassword(
          Password.createExistingPasswordFromRaw(
              password, userCredentialsOptional.get().getSalt()));
    }
    return false;
  }

  @Override
  public Customer getUserById(String id) throws UserNotFoundException {
    Optional<Customer> customer;
    try {
      customer = customerDAO.getUserById(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    if (customer.isPresent()) {
      return customer.get();
    } else {
      throw new UserNotFoundException(id).wrapAuditableException(id, ExceptionSeverity.LOW);
    }
  }

  @Override
  public Boolean isUserExist(String id) {
    return customerDAO.isUserExist(id);
  }

  public Boolean isEmailExist(String emailId) {
    return customerDAO.isEmailExist(emailId);
  }

  public Boolean updatePostalAddress(
      CustomerPostalAddressUpdateRequestPayload customerPostalAddressUpdateRequestPayload) {
    try {
      return customerDAO.updatePostalAddress(customerPostalAddressUpdateRequestPayload);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Boolean updateBankAccount(
      CustomerBankAccountUpdateRequestPayload customerBankAccountUpdateRequestPayload) {
    try {
      return customerDAO.updateBankAccount(customerBankAccountUpdateRequestPayload);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
