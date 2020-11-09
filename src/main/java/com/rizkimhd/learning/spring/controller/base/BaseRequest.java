package com.rizkimhd.learning.spring.controller.base;

import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Value
@Builder
public class BaseRequest<T> {
  T spec;
  Map queries;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  Date timestamp;
}
