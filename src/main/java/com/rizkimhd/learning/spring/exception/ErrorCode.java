package com.rizkimhd.learning.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  INVALID_CHARACTER(700, "Invalid character(s) found in the request"),
  MANDATORY_FIELD(701, "Mandatory field should not be empty"),
  CLIENT_ERROR(600, "Error(s) found within the request"),
  SERVER_ERROR(800, "Request(s) cannot be processed"),
  REDIRECT_INFO(900, "Resource(s) has ben moved");

  int code;
  String message;
}
