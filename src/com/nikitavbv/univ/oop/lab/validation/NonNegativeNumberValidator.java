package com.nikitavbv.univ.oop.lab.validation;

import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;

public class NonNegativeNumberValidator<T extends Number> implements Validator<T> {

  private final String message;

  public NonNegativeNumberValidator(String message) {
    this.message = message;
  }

  @Override
  public void validate(T input) {
    if (input.doubleValue() <= 0) {
      throw new NumberValueInvalidException(message);
    }
  }
}
