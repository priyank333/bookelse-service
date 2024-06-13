package com.bookelse.config.transaction.holder;

import com.bookelse.config.ApplicationContextConfiguration;
import org.springframework.jdbc.support.JdbcTransactionManager;

public final class TransactionManagerHolder {

  private TransactionManagerHolder() {}

  public static JdbcTransactionManager getJdbcTransactionManager() {
    return ApplicationContextConfiguration.getApplicationContext()
        .getBean("JDBCTransactionManager", JdbcTransactionManager.class);
  }
}
