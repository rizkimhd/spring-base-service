package com.rizkimhd.learning.spring.accessor;

import java.util.Locale;

public interface MessageAccessor {

  String getMessage(String key);

  String getMessage(String key, Locale locale);
}
