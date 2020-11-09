package com.rizkimhd.learning.spring.service;

import com.rizkimhd.learning.spring.controller.spec.HelloRequest;
import com.rizkimhd.learning.spring.controller.spec.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

  @Override
  public HelloResponse hello(String nickname) {
    return HelloResponse.builder()
        .name(nickname)
        .greeting("Hello")
        .build();
  }

  @Override
  public HelloResponse hello(HelloRequest spec) {
    return HelloResponse.builder()
        .name(spec.getName())
        .greeting("Hello")
        .build();
  }
}
