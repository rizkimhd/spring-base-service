package com.rizkimhd.learning.spring.controller.spec;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class HelloResponse {
  @NotBlank
  @With private String name;

  @NotBlank
  @With private String greeting;
}
