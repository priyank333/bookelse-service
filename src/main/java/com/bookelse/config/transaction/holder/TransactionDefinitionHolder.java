package com.bookelse.config.transaction.holder;

import com.bookelse.config.ApplicationContextConfiguration;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public final class TransactionDefinitionHolder {

  private TransactionDefinitionHolder() {}

  public static DefaultTransactionDefinition getDefaultTransactionDefinition() {
    return ApplicationContextConfiguration.getApplicationContext()
            .getBean("DefaultTransactionDefinition", DefaultTransactionDefinition.class);
  }
}