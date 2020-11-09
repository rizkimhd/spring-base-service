package com.rizkimhd.learning.spring.controller.base;

import com.rizkimhd.learning.spring.exception.ErrorCode;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BaseError {
  int code;
  ErrorCode reason;
  String message;
}
