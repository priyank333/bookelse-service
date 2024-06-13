package com.bookelse.config;

import com.bookelse.http.filter.CorrelationIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean<CorrelationIdFilter> correlationIdFilter() {
    FilterRegistrationBean<CorrelationIdFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new CorrelationIdFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}
