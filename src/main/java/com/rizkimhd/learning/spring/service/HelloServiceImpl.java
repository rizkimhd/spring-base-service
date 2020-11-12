package com.rizkimhd.learning.spring.service;

import com.rizkimhd.learning.spring.accessor.MessageAccessorImpl;
import com.rizkimhd.learning.spring.controller.spec.HelloRequest;
import com.rizkimhd.learning.spring.controller.spec.HelloResponse;
import com.rizkimhd.learning.spring.util.MessageKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

  @Autowired MessageAccessorImpl messageAccessor;

  @Override
  public HelloResponse hello(String nickname) {
    return HelloResponse.builder()
        .name(nickname)
        .greeting(messageAccessor.getMessage(MessageKey.COMMON_GREETING.getResource()))
        .build();
  }

  @Override
  public HelloResponse hello(HelloRequest spec) {
    return HelloResponse.builder()
        .name(spec.getName())
        .greeting(messageAccessor.getMessage(MessageKey.COMMON_GREETING.getResource()))
        .build();
  }
}
