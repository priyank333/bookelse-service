package com.bookelse.dao.mapper;

import com.bookelse.model.user.UserCredentials;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerCredentialsRowMapper implements RowMapper<UserCredentials> {
  @Override
  public UserCredentials mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new UserCredentials(rs.getString("email_id"), rs.getString("pwd"), rs.getString("salt"));
  }
}
