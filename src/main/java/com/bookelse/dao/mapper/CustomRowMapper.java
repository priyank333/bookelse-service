package com.bookelse.dao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import org.springframework.jdbc.core.RowMapper;

public abstract class CustomRowMapper<T> implements RowMapper<T> {
  protected Set<String> columns;

  public CustomRowMapper() {
    columns = new HashSet<>();
  }

  protected Set<String> loadColumns(ResultSet resultSet) {
    try {
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
        this.columns.add(resultSetMetaData.getColumnName(i));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return this.columns;
  }
}
