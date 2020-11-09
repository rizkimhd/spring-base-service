package com.rizkimhd.learning.spring.exception;

import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

@Getter
public class InvalidSpecException extends BaseException {
  String fieldName;

  public InvalidSpecException(double apiVersion,
                              String endpoint,
                              BaseRequest request,
                              ErrorCode errorCode) {
    super(apiVersion, endpoint, request, errorCode);
  }

  public InvalidSpecException(double apiVersion,
                              String endpoint,
                              BaseRequest request,
                              Pair<ErrorCode, String> errorCode) {
    this(apiVersion, endpoint, request, errorCode.getKey());
    this.fieldName = errorCode.getValue();
  }
}
