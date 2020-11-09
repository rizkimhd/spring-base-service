package com.rizkimhd.learning.spring.exception;

import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@Getter
public class NotImplementedException extends BaseException  {

  String resourceKey;
  String resourceId;

  public NotImplementedException(double apiVersion,
                                 String endpoint,
                                 BaseRequest request,
                                 ErrorCode errorCode) {
    super(apiVersion, endpoint, request, errorCode);
  }

  public NotImplementedException(double apiVersion,
                                 String endpoint,
                                 BaseRequest request,
                                 ErrorCode errorCode,
                                 Pair<String, String> resource) {
    this(apiVersion, endpoint, request, errorCode);
    this.resourceKey = resource.getKey();
    this.resourceId = resource.getValue();
  }
}
