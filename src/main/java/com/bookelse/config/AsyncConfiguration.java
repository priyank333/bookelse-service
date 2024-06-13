package com.bookelse.config;

import com.bookelse.exceptions.RuntimeExceptionAuditable;
import com.bookelse.model.exception.ExceptionSeverity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);

  @Bean("taskExecutor")
  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(25);
    executor.setMaxPoolSize(100);
    executor.setQueueCapacity(2500);
    executor.setThreadNamePrefix("AsyncThread-");
    executor.initialize();
    return executor;
  }
}
