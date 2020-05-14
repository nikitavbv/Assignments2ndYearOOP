package com.nikitavbv.univ.oop.lab.validation;

import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;

public class NonNegativeNumberValidator<T extends Number> implements Validator<T> {

  @Override
  public void validate(T input) {
    if (input.doubleValue() <= 0) {
      throw new NumberValueInvalidException("Number should be positive");
    }
  }
}
