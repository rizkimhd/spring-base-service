package com.rizkimhd.learning.spring.controller.base;

import java.util.Date;
import java.util.Locale;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Value
@Builder
public class BaseMetadata {
  double apiVersion;
  String endpoint;
  Locale locale;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  Date timestamp;
}
