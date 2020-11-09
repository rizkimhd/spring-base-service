package com.rizkimhd.learning.spring.controller.spec;

import com.rizkimhd.learning.spring.exception.ErrorCode;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@UtilityClass
public class HelloSpecValidator {

  public static Pair<ErrorCode, String> validateRequest(HelloRequest spec) {
    return validateName(spec.getName());
  }

  public static Pair<ErrorCode, String> validateName(String name) {
    if (StringUtils.isBlank(name)) {
      return new ImmutablePair<>(ErrorCode.MANDATORY_FIELD, "name");
    } else {
      return StringUtils.isAlpha(name) ? null : new ImmutablePair<>(ErrorCode.INVALID_CHARACTER, "name");
    }
  }

}
