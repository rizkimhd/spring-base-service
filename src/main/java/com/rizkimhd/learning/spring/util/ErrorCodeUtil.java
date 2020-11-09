package com.rizkimhd.learning.spring.util;

import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import com.rizkimhd.learning.spring.exception.ErrorCode;
import com.rizkimhd.learning.spring.exception.InvalidSpecException;
import com.rizkimhd.learning.spring.exception.MandatoryFieldException;
import com.rizkimhd.learning.spring.exception.NotImplementedException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@UtilityClass
public class ErrorCodeUtil {

  public static void handleSpecValidatorErrorCode(double apiVersion,
                                                  String endpoint,
                                                  BaseRequest request,
                                                  Pair<ErrorCode, String> errorCode) {
    if (errorCode != null) {
      switch (errorCode.getKey()) {
        case INVALID_CHARACTER:
          throw new InvalidSpecException(apiVersion, endpoint, request, errorCode);
        case MANDATORY_FIELD:
          throw new MandatoryFieldException(apiVersion, endpoint, request, errorCode);
      }
    }
  }

  public static void handleResourceNotImplemented(double apiVersion,
                                                  String endpoint,
                                                  BaseRequest request,
                                                  Pair<String, String> resource) {
    throw new NotImplementedException(apiVersion, endpoint, request, ErrorCode.SERVER_ERROR, resource);
  }

}
