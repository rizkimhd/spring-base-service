package com.rizkimhd.learning.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class BaseServiceApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(BaseServiceApplication.class, args);
  }

  @Bean
  public LocaleResolver localeResolver() {
    String cookieLocaleName = "locale";
    int cookieAgeInSeconds = 5 * 60;

    String sessionLocaleAttributeName = cookieLocaleName;
    String sessionTimeZoneAttributeName = "timezone";

    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setCookieName(cookieLocaleName);
    cookieLocaleResolver.setCookieMaxAge(cookieAgeInSeconds);

    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setTimeZoneAttributeName(sessionLocaleAttributeName);
    sessionLocaleResolver.setLocaleAttributeName(sessionTimeZoneAttributeName);

    return cookieLocaleResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("locale");
    localeChangeInterceptor.setIgnoreInvalidLocale(false);
    return localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}
