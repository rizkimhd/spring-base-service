package com.rizkimhd.learning.spring.controller.base;

import com.rizkimhd.learning.spring.exception.ErrorCode;
import com.rizkimhd.learning.spring.util.RestControllerUtil;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This implementation was inspired by this this post
 *
 * @see <a href="https://thepracticaldeveloper.com/custom-error-handling-rest-controllers-spring-boot/#customize-errorcontroller-to-avoid-html-whitelabel-error-pages">
 * https://thepracticaldeveloper.com
 * </a>
 */
@Slf4j
@RestController
@RequestMapping(value = BaseErrorController.ERROR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseErrorController extends AbstractErrorController {

  static final String ERROR_PATH = "/error";

  public BaseErrorController(final ErrorAttributes errorAttributes) {
    super(errorAttributes, Collections.emptyList());
  }

  @RequestMapping
  public ResponseEntity defaultErrorHandling(HttpServletRequest request) {
    HttpStatus status = this.getStatus(request);
    Map<String, Object> errorAttributes = this.getErrorAttributes(request, false);

    BaseError error = constructError(status, errorAttributes);

    return new ResponseEntity<>(RestControllerUtil.constructErrorResponse(getEndpoint(errorAttributes), error), status);
  }

  private String getEndpoint(Map errorAttributes) {
    return errorAttributes.get("path").toString();
  }

  private BaseError constructError(HttpStatus status, Map errorAttributes) {
    BaseError error = null;
    if (status.is4xxClientError()) {
      error = BaseError.builder()
          .code(ErrorCode.CLIENT_ERROR.getCode())
          .reason(ErrorCode.CLIENT_ERROR)
          .message(constructErrorMessage(errorAttributes))
          .build();
    }

    if (status.is5xxServerError()) {
      error = BaseError.builder()
          .code(ErrorCode.SERVER_ERROR.getCode())
          .reason(ErrorCode.SERVER_ERROR)
          .message(constructErrorMessage(errorAttributes))
          .build();
    }

    return error;
  }

  private String constructErrorMessage(Map errorAttributes) {
    return String.format(
        "ERR %s (%d): %s",
        errorAttributes.get("error"),
        Integer.parseInt(errorAttributes.get("status").toString()),
        errorAttributes.get("message")
    );
  }

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }
}
