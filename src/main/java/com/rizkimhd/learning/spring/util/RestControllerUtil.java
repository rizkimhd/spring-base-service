package com.rizkimhd.learning.spring.util;

import com.rizkimhd.learning.spring.controller.base.BaseError;
import com.rizkimhd.learning.spring.controller.base.BaseMetadata;
import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import com.rizkimhd.learning.spring.controller.base.BaseResponse;
import java.util.Date;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

@Slf4j
@UtilityClass
public class RestControllerUtil {

  private BaseResponse constructResponse(BaseRequest request) {
    return BaseResponse.builder()
        .request(request)
        .build();
  }

  private BaseResponse constructResponse(double apiVersion, String endpoint, BaseRequest request) {
    return constructResponse(request)
        .withMetadata(
            BaseMetadata.builder()
                .locale(LocaleContextHolder.getLocale())
                .endpoint(endpoint)
                .apiVersion(apiVersion)
                .timestamp(new Date())
                .build()
        );
  }

  private double detectApiVersion(String endpoint) {
    try {
      return StringUtils.isBlank(endpoint.split("/")[2]) ?
             0D : Double.parseDouble(endpoint.split("/")[2].substring(1));
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      log.error("Failed to determine API version from endpoint '{}' with ERR {}", endpoint, e.getMessage());
      return 0D;
    }
  }

  public static BaseResponse constructErrorResponse(String endpoint,
                                                    BaseError error) {

    return constructErrorResponse(detectApiVersion(endpoint), endpoint, null, error);
  }

  public static BaseResponse constructErrorResponse(double apiVersion,
                                                    String endpoint,
                                                    BaseRequest request,
                                                    BaseError error) {
    return constructResponse(apiVersion, endpoint, request).withError(error);
  }

  public static BaseResponse constructResponse(double apiVersion,
                                               String endpoint,
                                               BaseRequest request,
                                               Object response) {
    return constructResponse(apiVersion, endpoint, request).withData(response);
  }

  public static BaseRequest constructRequestQueries(Map queries) {
    return BaseRequest.builder()
        .queries(queries)
        .timestamp(new Date())
        .build();
  }
}
