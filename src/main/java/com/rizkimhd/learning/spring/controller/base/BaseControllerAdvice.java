package com.rizkimhd.learning.spring.controller.base;

import com.rizkimhd.learning.spring.exception.InvalidSpecException;
import com.rizkimhd.learning.spring.exception.MandatoryFieldException;
import com.rizkimhd.learning.spring.exception.NotImplementedException;
import com.rizkimhd.learning.spring.util.RestControllerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseControllerAdvice {

  @ExceptionHandler(value = InvalidSpecException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public BaseResponse invalidSpecException(InvalidSpecException e) {
    BaseError error = BaseError.builder()
        .code(e.getErrorCode().getCode())
        .reason(e.getErrorCode())
        .message(String.format("'%s' contains invalid character(s)", e.getFieldName()))
        .build();

    return RestControllerUtil.constructErrorResponse(e.getApiVersion(), e.getEndpoint(), e.getRequest(), error);
  }

  @ExceptionHandler(value = MandatoryFieldException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public BaseResponse mandatoryFieldException(MandatoryFieldException e) {
    BaseError error = BaseError.builder()
        .code(e.getErrorCode().getCode())
        .reason(e.getErrorCode())
        .message(String.format("Mandatory field '%s' should not be empty", e.getFieldName()))
        .build();

    return RestControllerUtil.constructErrorResponse(e.getApiVersion(), e.getEndpoint(), e.getRequest(), error);
  }

  @ExceptionHandler(value = NotImplementedException.class)
  @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
  public BaseResponse mandatoryFieldException(NotImplementedException e) {
    BaseError error = BaseError.builder()
        .code(e.getErrorCode().getCode())
        .reason(e.getErrorCode())
        .message(
            String.format("%s '%s' has not been implemented",
            StringUtils.capitalize(e.getResourceKey()), e.getEndpoint()))
        .build();

    return RestControllerUtil.constructErrorResponse(e.getApiVersion(), e.getEndpoint(), e.getRequest(), error);
  }


}
