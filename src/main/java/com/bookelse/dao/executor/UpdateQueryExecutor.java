package com.bookelse.dao.executor;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateQueryExecutor<E> extends SQLQueryExecutor {
  private int noOfRowsAffected;

  public UpdateQueryExecutor(String query, JdbcTemplate jdbcTemplate) throws SQLException {
    super(query, jdbcTemplate);
  }

  @Override
  public void execute() {
    try {
      this.noOfRowsAffected = getJdbcTemplate().update(getQuery(), convertArgsIntoObjectArr());
    } catch (DataAccessException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }

  public int getNoOfRowsAffected() {
    return noOfRowsAffected;
  }
}
