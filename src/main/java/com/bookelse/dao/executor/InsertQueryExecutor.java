package com.bookelse.dao.executor;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertQueryExecutor<E> extends SQLQueryExecutor {

  private int noOfRowsAffected;

  public InsertQueryExecutor(String query, JdbcTemplate jdbcTemplate) {
    super(query, jdbcTemplate);
  }

  @Override
  public void execute() throws DataAccessException {
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
