package com.bookelse.dao;

import com.bookelse.config.ApplicationContextConfiguration;
import com.bookelse.dao.executor.InsertQueryExecutor;
import com.bookelse.dao.executor.SelectQueryExecutor;
import com.bookelse.dao.executor.UpdateQueryExecutor;
import com.bookelse.dao.mapper.CustomerCredentialsRowMapper;
import com.bookelse.dao.mapper.CustomerRowMapper;
import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.user.Customer;
import com.bookelse.model.user.UserCredentials;
import com.bookelse.payload.customer.CustomerBankAccountUpdateRequestPayload;
import com.bookelse.payload.customer.CustomerPostalAddressUpdateRequestPayload;
import com.bookelse.util.datetime.DateTimeUtility;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("CustomerDAO")
@PropertySource("classpath:sql/users-query.properties")
public class CustomerDAO implements UserDAO<Customer> {

  private final JdbcTemplate jdbcTemplate;

  @Value("${customer.insert-new-customer-data}")
  private String insertNewCustomerDataQuery;

  @Value("${customer.select-customer-cred}")
  private String selectCustomerCredQuery;

  @Value("${customer.select-customer}")
  private String selectCustomerByUserIdQuery;

  @Value("${customer.select-all-customers}")
  private String selectAllCustomersQuery;

  @Value("${customer.is-email-exist}")
  private String isEmailExistQuery;

  @Value("${customer.is-customer-exist}")
  private String isCustomerExistQuery;

  @Value("${customer.update-postal-address}")
  private String updatePostalAddressQuery;

  @Value("${customer.update-bank-acc}")
  private String updateBankAccountQuery;

  public CustomerDAO() {
    jdbcTemplate =
        ApplicationContextConfiguration.getApplicationContext().getBean(JdbcTemplate.class);
  }

  @Override
  public Customer registerUserInDB(Customer user) throws SQLException {

    InsertQueryExecutor<?> insertQueryExecutor =
        new InsertQueryExecutor<>(insertNewCustomerDataQuery, jdbcTemplate);
    insertQueryExecutor
        .addArgument(user.getUserId().getId())
        .addArgument(user.getPassword().getPassword())
        .addArgument(user.getPassword().getSalt())
        .addArgument(user.getName().getFirstName())
        .addArgument(user.getName().getLastName())
        .addArgument(user.getEmailId().getEmailId())
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(user.getCreatedOn()))
        .addArgument(DateTimeUtility.zonedDateTimeToTimestamp(user.getUpdatedOn()))
        .addArgument(user.getContact().getContact())
        .addArgument(user.getContact().getCountryCode())
        .addArgument(user.getZoneId().getZoneIdName());
    insertQueryExecutor.execute();
    return insertQueryExecutor.getNoOfRowsAffected() > 0 ? user : null;
  }

  @Override
  public Optional<UserCredentials> getUserCredentials(String id) throws SQLException {
    SelectQueryExecutor<CustomerCredentialsRowMapper, UserCredentials> selectQueryExecutor =
        new SelectQueryExecutor<>(
            selectCustomerCredQuery, jdbcTemplate, new CustomerCredentialsRowMapper());
    selectQueryExecutor.addArgument(id);
    selectQueryExecutor.execute();
    return selectQueryExecutor.getFirstResult();
  }

  @Override
  public Optional<Customer> getUserById(String id) throws SQLException {
    SelectQueryExecutor<CustomerRowMapper, Customer> selectQueryExecutor =
        new SelectQueryExecutor<>(
            selectCustomerByUserIdQuery, jdbcTemplate, new CustomerRowMapper());
    selectQueryExecutor.addArgument(id);
    selectQueryExecutor.execute();
    return selectQueryExecutor.getFirstResult();
  }

  @Override
  public List<Customer> listAllUsers() throws SQLException {
    SelectQueryExecutor<CustomerRowMapper, Customer> selectQueryExecutor =
        new SelectQueryExecutor<>(selectAllCustomersQuery, jdbcTemplate, new CustomerRowMapper());
    selectQueryExecutor.execute();
    return selectQueryExecutor.getResults().toList();
  }

  @Override
  public Boolean isUserExist(String userId) {
    try {
      Integer count = jdbcTemplate.queryForObject(isCustomerExistQuery, Integer.class, userId);
      return count != null && count > 0;
    } catch (DataAccessException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }

  public Boolean isEmailExist(String emailId) {
    try {
      Integer count = jdbcTemplate.queryForObject(isEmailExistQuery, Integer.class, emailId);
      return count != null && count > 0;
    } catch (DataAccessException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }

  public Boolean updatePostalAddress(
      CustomerPostalAddressUpdateRequestPayload customerPostalAddressUpdateRequestPayload)
      throws SQLException {
    UpdateQueryExecutor<?> updateQueryExecutor =
        new UpdateQueryExecutor<>(updatePostalAddressQuery, jdbcTemplate);
    updateQueryExecutor
        .addArgument(customerPostalAddressUpdateRequestPayload.address1())
        .addArgument(customerPostalAddressUpdateRequestPayload.address2())
        .addArgument(customerPostalAddressUpdateRequestPayload.postalCode())
        .addArgument(customerPostalAddressUpdateRequestPayload.city())
        .addArgument(customerPostalAddressUpdateRequestPayload.state())
        .addArgument(customerPostalAddressUpdateRequestPayload.country())
        .addArgument(
            DateTimeUtility.zonedDateTimeToTimestamp(DateTimeUtility.getCurrentUTCDateTime()))
        .addArgument(customerPostalAddressUpdateRequestPayload.customerId());
    updateQueryExecutor.execute();
    return updateQueryExecutor.getNoOfRowsAffected() > 0;
  }

  public Boolean updateBankAccount(
      CustomerBankAccountUpdateRequestPayload customerBankAccountUpdateRequestPayload)
      throws SQLException {
    UpdateQueryExecutor<?> updateQueryExecutor =
        new UpdateQueryExecutor<>(updateBankAccountQuery, jdbcTemplate);
    updateQueryExecutor
        .addArgument(customerBankAccountUpdateRequestPayload.bankAccountName())
        .addArgument(customerBankAccountUpdateRequestPayload.bankAccountNo())
        .addArgument(customerBankAccountUpdateRequestPayload.bankIfscCode())
        .addArgument(
            DateTimeUtility.zonedDateTimeToTimestamp(DateTimeUtility.getCurrentUTCDateTime()))
        .addArgument(customerBankAccountUpdateRequestPayload.customerId());
    updateQueryExecutor.execute();
    return updateQueryExecutor.getNoOfRowsAffected() > 0;
  }
}
