package com.rizkimhd.learning.spring.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageKey {
  COMMON_GREETING("common.greeting");

  String resource;
}
