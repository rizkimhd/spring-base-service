package com.rizkimhd.learning.spring.accessor;

import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageAccessorImpl implements MessageAccessor {

  @Autowired MessageSource messageSource;

  @Override
  public String getMessage(String key) {
    return this.getMessage(key, LocaleContextHolder.getLocale());
  }

  @Override
  public String getMessage(String key, Locale locale) {
    return messageSource.getMessage(key, null, locale);
  }
}
