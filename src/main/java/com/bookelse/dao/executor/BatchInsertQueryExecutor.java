package com.bookelse.dao.executor;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import java.sql.SQLException;
import java.util.Arrays;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

public class BatchInsertQueryExecutor extends SQLQueryExecutor {
  private final BatchPreparedStatementSetter batchPreparedStatementSetter;
  private int noOfRowsAffected;

  public BatchInsertQueryExecutor(
      String query,
      BatchPreparedStatementSetter batchPreparedStatementSetter,
      JdbcTemplate jdbcTemplate)
      throws SQLException {
    super(query, jdbcTemplate);
    this.batchPreparedStatementSetter = batchPreparedStatementSetter;
  }

  @Override
  public void execute() {
    try {
      this.noOfRowsAffected =
          Arrays.stream(getJdbcTemplate().batchUpdate(getQuery(), batchPreparedStatementSetter))
              .filter(row -> row == 1)
              .sum();
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
