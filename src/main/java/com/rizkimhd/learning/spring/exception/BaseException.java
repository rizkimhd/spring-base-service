package com.rizkimhd.learning.spring.exception;


import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
  double apiVersion;
  String endpoint;
  BaseRequest request;
  ErrorCode errorCode;
}
