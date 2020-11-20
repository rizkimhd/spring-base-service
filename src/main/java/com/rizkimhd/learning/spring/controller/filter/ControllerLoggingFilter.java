package com.rizkimhd.learning.spring.controller.filter;

import com.rizkimhd.learning.spring.controller.filter.helper.BufferedRequestWrapper;
import com.rizkimhd.learning.spring.controller.filter.helper.BufferedResponseWrapper;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This implementation was based on
 *
 * @see <a href="https://stackoverflow.com/questions/33744875/spring-boot-how-to-log-all-requests-and-responses-with-exceptions-in-single-pl">
 * https://stackoverflow.com
 * </a>
 */
@Slf4j
@Component
public class ControllerLoggingFilter implements Filter {

  @Value("${logging.http.request}")
  boolean requestLoggingEnabled;

  @Value("${logging.http.response}")
  boolean responseLoggingEnabled;

  @Value("${springdoc.swagger-ui.path}")
  String swaggerPath;

  @Value("${springdoc.api-docs.path}")
  String openApiPath;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    long startTimeInMs = new Date().getTime();
    String requestId = String.format("REQ%s", UUID.randomUUID().getLeastSignificantBits());
    log.info("[{}]|Incoming", requestId);

    BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper((HttpServletRequest) request);
    BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper((HttpServletResponse) response);

    if (isExcluded(bufferedRequest.getServletPath())) {
      chain.doFilter(bufferedRequest, bufferedResponse);
    } else {
      if (requestLoggingEnabled) {
        logRequest(requestId, bufferedRequest);
      }

      chain.doFilter(bufferedRequest, bufferedResponse);

      if (responseLoggingEnabled) {
        logResponse(requestId, bufferedResponse);
      }
    }

    long endTimeInMs = new Date().getTime();
    log.info("[{}]|Completed within {}ms", requestId, endTimeInMs - startTimeInMs);
  }

  private void logRequest(String requestId, BufferedRequestWrapper bufferedRequest) {
    try {
      String message = "REST Request - " +
          "[HTTP METHOD:" + bufferedRequest.getMethod() + "] " +
          "[PATH INFO:" + bufferedRequest.getServletPath() + "] " +
          "[REQUEST PARAMETERS:" + getTypesafeRequestMap(bufferedRequest) + "] " +
          "[REQUEST BODY:" + bufferedRequest.getRequestBody() + "] " +
          "[REMOTE ADDRESS:" + bufferedRequest.getRemoteAddr() + "]";
      log.info("[{}]|{}", requestId, message);
    } catch (IOException e) {
      log.error("[{}]|Failed to log request", requestId);
    }

  }

  private void logResponse(String requestId, BufferedResponseWrapper bufferedResponse) {
    String message = "REST Response - " +
        "[RESPONSE:" + bufferedResponse.getContent() + "]";
    log.info("[{}]|{}", requestId, message);
  }

  private Map<String, String> getTypesafeRequestMap(HttpServletRequest request) {
    Map<String, String> typesafeRequestMap = new HashMap<>();
    Enumeration<?> requestParamNames = request.getParameterNames();
    while (requestParamNames.hasMoreElements()) {
      String requestParamName = (String) requestParamNames.nextElement();
      String requestParamValue;
      if (requestParamName.equalsIgnoreCase("password")) {
        requestParamValue = "********";
      } else {
        requestParamValue = request.getParameter(requestParamName);
      }
      typesafeRequestMap.put(requestParamName, requestParamValue);
    }
    return typesafeRequestMap;
  }

  private boolean isExcluded(String path) {
    return path.toLowerCase().contains(swaggerPath.toLowerCase().split("\\.")[0])
        || path.toLowerCase().contains(openApiPath.toLowerCase());
  }

}