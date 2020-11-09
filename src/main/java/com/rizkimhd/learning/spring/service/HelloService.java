package com.rizkimhd.learning.spring.service;

import com.rizkimhd.learning.spring.controller.spec.HelloRequest;
import com.rizkimhd.learning.spring.controller.spec.HelloResponse;

public interface HelloService {
  HelloResponse hello(String nickname);

  HelloResponse hello(HelloRequest spec);
}
