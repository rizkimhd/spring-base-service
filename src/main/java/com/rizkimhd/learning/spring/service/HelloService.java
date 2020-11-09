package com.rizkimhd.learning.spring.service;

import com.rizkimhd.learning.spring.controller.spec.HelloResponse;
import com.rizkimhd.learning.spring.controller.spec.HelloRequest;

public interface HelloService {
  HelloResponse hello(String nickname);

  HelloResponse hello(HelloRequest spec);
}
