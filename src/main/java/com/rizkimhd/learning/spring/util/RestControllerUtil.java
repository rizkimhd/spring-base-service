package com.rizkimhd.learning.spring.util;

import com.rizkimhd.learning.spring.controller.base.BaseError;
import com.rizkimhd.learning.spring.controller.base.BaseMetadata;
import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import com.rizkimhd.learning.spring.controller.base.BaseResponse;
import java.util.Date;
import java.util.Map;
import lombok.experimental.UtilityClass;
import org.springframework.context.i18n.LocaleContextHolder;

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

  public static BaseResponse constructErrorResponse(String endpoint,
                                                    BaseError error) {
    return constructErrorResponse(0D, endpoint, null, error);
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

  public static BaseRequest ConstructGetRequest(Map queries) {
    return BaseRequest.builder()
        .queries(queries)
        .timestamp(new Date())
        .build();
  }
}
