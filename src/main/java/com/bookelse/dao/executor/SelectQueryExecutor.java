package com.bookelse.dao.executor;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SelectQueryExecutor<R extends RowMapper<O>, O> extends SQLQueryExecutor {

  private final R rowMapper;
  private Stream<O> output;

  public SelectQueryExecutor(String query, JdbcTemplate jdbcTemplate, R rowMapper)
      throws SQLException {
    super(query, jdbcTemplate);
    this.rowMapper = rowMapper;
  }

  @Override
  public void execute() {
    this.output =
        getJdbcTemplate().queryForStream(getQuery(), getRowMapper(), convertArgsIntoObjectArr());
  }

  public R getRowMapper() {
    return rowMapper;
  }

  public Stream<O> getResults() {
    return this.output;
  }

  public Optional<O> getFirstResult() {
    Objects.requireNonNull(output);

    return output.findFirst();
  }
}
