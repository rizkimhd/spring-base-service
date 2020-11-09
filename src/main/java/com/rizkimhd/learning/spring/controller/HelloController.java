package com.rizkimhd.learning.spring.controller;

import com.rizkimhd.learning.spring.controller.base.BaseRequest;
import com.rizkimhd.learning.spring.controller.base.BaseResponse;
import com.rizkimhd.learning.spring.controller.spec.HelloRequest;
import com.rizkimhd.learning.spring.controller.spec.HelloResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface HelloController {

  BaseResponse<HelloResponse> getHello(String name);

  BaseResponse<HelloResponse> postHello(BaseRequest<HelloRequest> request);

  BaseResponse<HelloResponse> putHello(BaseRequest<HelloRequest> request);

  BaseResponse<HelloResponse> patchHello(BaseRequest<HelloRequest> request);

  BaseResponse<HelloResponse> deleteHello(String name);
  
}
