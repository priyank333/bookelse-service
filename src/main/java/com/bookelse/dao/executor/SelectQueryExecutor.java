package com.bookelse.dao.executor;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SelectQueryExecutor<R extends RowMapper<O>, O> extends SQLQueryExecutor {

  private final R rowMapper;
  private Stream<O> output;

  public SelectQueryExecutor(String query, JdbcTemplate jdbcTemplate, R rowMapper) {
    super(query, jdbcTemplate);
    this.rowMapper = rowMapper;
  }

  @Override
  public void execute() {
    try {
      this.output =
          getJdbcTemplate().queryForStream(getQuery(), getRowMapper(), convertArgsIntoObjectArr());
    } catch (DataAccessException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.HIGH);
      throw runtimeExceptionAuditable;
    }
  }

  public R getRowMapper() {
    return rowMapper;
  }

  public Stream<O> getResults() {
    return this.output;
  }

  public Optional<O> getFirstResult() {
    try {
      Objects.requireNonNull(output);
    } catch (NullPointerException e) {
      RuntimeExceptionAuditable runtimeExceptionAuditable = new RuntimeExceptionAuditable(e);
      runtimeExceptionAuditable.wrapAuditableException("", ExceptionSeverity.LOW);
      throw runtimeExceptionAuditable;
    }
    return output.findFirst();
  }
}
