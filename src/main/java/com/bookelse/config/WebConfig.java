package com.bookelse.config;

import com.bookelse.http.filter.CorrelationIdFilter;
import com.bookelse.http.interceptor.CorrelationIdInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final CorrelationIdFilter correlationIdFilter;
  private final CorrelationIdInterceptor correlationIdInterceptor;

  public WebConfig(
      @Qualifier("CorrelationIdInterceptor") CorrelationIdInterceptor correlationIdInterceptor,
      @Qualifier("CorrelationIdFilter") CorrelationIdFilter correlationIdFilter) {
    this.correlationIdInterceptor = correlationIdInterceptor;
    this.correlationIdFilter = correlationIdFilter;
  }

  @Bean
  public FilterRegistrationBean<CorrelationIdFilter> correlationIdFilter() {
    FilterRegistrationBean<CorrelationIdFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(correlationIdFilter);
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(correlationIdInterceptor).addPathPatterns("/**");
  }
}
