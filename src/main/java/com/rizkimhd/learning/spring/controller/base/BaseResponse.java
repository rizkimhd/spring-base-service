package com.rizkimhd.learning.spring.controller.base;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class BaseResponse<T> {
  @With BaseError error;
  @With T data;
  BaseRequest request;
  @With BaseMetadata metadata;
}
