package com.bookelse.dao.mapper;

import static com.bookelse.util.datetime.DateTimeUtility.*;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.common.*;
import com.bookelse.model.exception.ExceptionSeverity;
import com.bookelse.model.id.SequenceId;
import com.bookelse.model.user.*;
import com.bookelse.util.datetime.ZoneId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class CustomerRowMapper extends CustomRowMapper<Customer> {

  @Override
  public Customer mapRow(ResultSet rs, int rowNum) {
    Set<String> columns = loadColumns(rs);
    try {
      ZoneId zoneId =
          columns.contains("zone_id")
              ? ZoneId.getInstanceByZoneIdName(rs.getString("zone_id"))
              : ZoneId.UTC;
      if (!columns.contains("user_id")) {
        RuntimeExceptionAuditable runtimeExceptionAuditable =
            new RuntimeExceptionAuditable(
                "SQL query error : user_id column is not found while retrieving customer details.");
        runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
        throw runtimeExceptionAuditable;
      }
      return new Customer(
          new SequenceId(rs.getString("user_id")),
          columns.containsAll(List.of("pwd", "salt"))
              ? Password.createExistingPasswordObject(rs.getString("pwd"), rs.getString("salt"))
              : null,
          columns.contains("first_name") && columns.contains("last_name")
              ? new PersonName(rs.getString("first_name"), rs.getString("last_name"))
              : null,
          columns.contains("email_id") ? new EmailId(rs.getString("email_id")) : null,
          columns.containsAll(List.of("country_code", "contact"))
              ? new Contact(rs.getString("country_code"), rs.getString("contact"))
              : null,
          columns.containsAll(
                  List.of("address_1", "address_2", "city", "state", "country", "postal_code"))
              ? new Address(
                  rs.getString("address_1"),
                  rs.getString("address_2"),
                  rs.getString("city"),
                  rs.getString("state"),
                  rs.getString("country"),
                  rs.getString("postal_code"))
              : null,
          columns.containsAll(List.of("bnk_acc_name", "bnk_acc_no", "bnk_ifsc_code"))
              ? new BankAccount(
                  rs.getString("bnk_acc_name"),
                  rs.getString("bnk_acc_no"),
                  rs.getString("bnk_ifsc_code"))
              : null,
          timestampToZonedDateTime(rs.getTimestamp("created_on"), zoneId),
          timestampToZonedDateTime(rs.getTimestamp("lst_update_on"), zoneId),
          zoneId);
    } catch (SQLException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }
}
