package com.rizkimhd.learning.spring.exception;

import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

@Getter
public class MandatoryFieldException extends BaseException {
  String fieldName;

  public MandatoryFieldException(double apiVersion,
                                 String endpoint,
                                 BaseRequest request,
                                 ErrorCode errorCode) {
    super(apiVersion, endpoint, request, errorCode);
  }

  public MandatoryFieldException(double apiVersion,
                                 String endpoint,
                                 BaseRequest request,
                                 Pair<ErrorCode, String> errorCode) {
    this(apiVersion, endpoint, request, errorCode.getKey());
    this.fieldName = errorCode.getValue();
  }
}
