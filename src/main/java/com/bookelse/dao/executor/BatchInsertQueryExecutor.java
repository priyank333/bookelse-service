package com.bookelse.dao.executor;

import java.sql.SQLException;
import java.util.Arrays;
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
    this.noOfRowsAffected =
        Arrays.stream(getJdbcTemplate().batchUpdate(getQuery(), batchPreparedStatementSetter))
            .filter(row -> row == 1)
            .sum();
  }

  public int getNoOfRowsAffected() {
    return noOfRowsAffected;
  }
}
