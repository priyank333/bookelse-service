package com.bookelse.dao.executor;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertQueryExecutor<E> extends SQLQueryExecutor {

  private int noOfRowsAffected;

  public InsertQueryExecutor(String query, JdbcTemplate jdbcTemplate) throws SQLException {
    super(query, jdbcTemplate);
  }

  @Override
  public void execute() {
    this.noOfRowsAffected = getJdbcTemplate().update(getQuery(), convertArgsIntoObjectArr());
  }

  public int getNoOfRowsAffected() {
    return noOfRowsAffected;
  }
}
