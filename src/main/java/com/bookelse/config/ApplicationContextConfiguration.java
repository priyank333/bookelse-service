package com.bookelse.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextConfiguration {
  private static ApplicationContext applicationContext;

  public ApplicationContextConfiguration(ApplicationContext applicationContext) {
    ApplicationContextConfiguration.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
