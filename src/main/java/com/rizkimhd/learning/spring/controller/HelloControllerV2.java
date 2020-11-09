package com.rizkimhd.learning.spring.controller;

import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import com.rizkimhd.learning.spring.controller.base.BaseResponse;
import com.rizkimhd.learning.spring.controller.spec.HelloRequest;
import com.rizkimhd.learning.spring.controller.spec.HelloResponse;
import com.rizkimhd.learning.spring.controller.spec.HelloSpecValidator;
import com.rizkimhd.learning.spring.exception.ErrorCode;
import com.rizkimhd.learning.spring.service.HelloService;
import com.rizkimhd.learning.spring.util.ErrorCodeUtil;
import com.rizkimhd.learning.spring.util.RestControllerUtil;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = HelloControllerV2.BASE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloControllerV2 implements HelloController {

  static final String BASE_ENDPOINT = "/api/v2/hello";
  private static final double API_VERSION = 2.0;

  @Autowired
  HelloService helloService;

  @GetMapping(value = "/{name}")
  public BaseResponse<HelloResponse> getHello(@PathVariable(value = "name") String name) {
    BaseRequest request = RestControllerUtil.constructRequestQueries(Map.of("name", name));

    Pair<ErrorCode, String> errorCode = HelloSpecValidator.validateName(name);
    handleSpecValidation(request, errorCode);

    return constructResult(request, helloService.hello(name));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse<HelloResponse> postHello(@RequestBody BaseRequest<HelloRequest> request) {
    handleMethodNotImplemented(request, BASE_ENDPOINT, HttpMethod.POST);
    return null;
  }

  /**
   * @param request
   *
   * @return use RequestMethod.PUT when you'd like to update a singular resource
   *
   * @see <a href="https://restfulapi.net/rest-put-vs-post>
   * https://restfulapi.net
   * </a>
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse<HelloResponse> putHello(@RequestBody BaseRequest<HelloRequest> request) {
    handleMethodNotImplemented(request, BASE_ENDPOINT, HttpMethod.PUT);
    return null;
  }

  /**
   * @param request
   *
   * @return use RequestMethod.PATCH when you'd like to update a part of the resource
   *
   * @see <a href="https://restfulapi.net/rest-put-vs-post>
   * https://restfulapi.net
   * </a>
   */
  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse<HelloResponse> patchHello(@RequestBody BaseRequest<HelloRequest> request) {
    handleMethodNotImplemented(request, BASE_ENDPOINT, HttpMethod.PATCH);
    return null;
  }

  @DeleteMapping(value = "/{name}")
  public BaseResponse<HelloResponse> deleteHello(@PathVariable(value = "name") String name) {
    handleMethodNotImplemented(
        RestControllerUtil.constructRequestQueries(Map.of("name", name)),
        BASE_ENDPOINT,
        HttpMethod.DELETE
    );
    return null;
  }

  private void handleMethodNotImplemented(BaseRequest<HelloRequest> request,
                                          String endpoint,
                                          HttpMethod httpMethod) {
    ErrorCodeUtil.handleResourceNotImplemented(
        API_VERSION,
        BASE_ENDPOINT,
        request,
        new ImmutablePair<>("endpoint", String.format("%s (%s)", endpoint, httpMethod.name()))
    );
  }

  private void handleSpecValidation(BaseRequest<HelloRequest> request,
                                    Pair<ErrorCode, String> errorCode) {
    ErrorCodeUtil.handleSpecValidatorErrorCode(API_VERSION, BASE_ENDPOINT, request, errorCode);
  }

  private BaseResponse<HelloResponse> constructResult(BaseRequest<HelloRequest> request,
                                                      HelloResponse response) {
    return RestControllerUtil.constructResponse(API_VERSION, BASE_ENDPOINT, request, response);
  }
}
