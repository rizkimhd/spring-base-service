package com.rizkimhd.learning.spring.controller.spec;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class HelloRequest {
  @NotBlank private String name;
}
