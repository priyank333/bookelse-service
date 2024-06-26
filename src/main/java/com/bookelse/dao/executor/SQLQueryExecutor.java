package com.bookelse.dao.executor;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class SQLQueryExecutor {
  private final JdbcTemplate jdbcTemplate;
  private final String query;
  private final LinkedList<Object> arguments;
  private final DataSource dataSource;
  private final Connection connection;

  public SQLQueryExecutor(String query, JdbcTemplate jdbcTemplate) {
    this.query = query;
    this.jdbcTemplate = jdbcTemplate;
    this.dataSource = this.jdbcTemplate.getDataSource();
    if (this.dataSource != null) {
      try {
        this.connection = this.dataSource.getConnection();
      } catch (SQLException e) {
        throw new RuntimeExceptionAuditable(e).wrapAuditableException("", ExceptionSeverity.HIGH);
      }
    } else {
      throw new RuntimeExceptionAuditable("DB Connection is required")
          .wrapAuditableException("", ExceptionSeverity.HIGH);
    }
    this.arguments = new LinkedList<>();
  }

  public abstract void execute();

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public String getQuery() {
    return query;
  }

  public SQLQueryExecutor addArgument(Object argument) {
    this.arguments.add(argument);
    return this;
  }

  public SQLQueryExecutor removeLast() {
    if (this.arguments != null && !this.arguments.isEmpty()) this.arguments.removeLast();
    return this;
  }

  protected Object[] convertArgsIntoObjectArr() {
    return arguments.toArray(new Object[0]);
  }

  public LinkedList<Object> getArguments() {
    return arguments;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public Connection getConnection() {
    return connection;
  }
}
