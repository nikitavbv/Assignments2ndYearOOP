package com.nikitavbv.univ.oop.lab.validation;

import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;

public class NumberInBoundsValidator<T extends Number> implements Validator<T> {

  private final String message;
  private final T minBound;
  private final T maxBound;

  public NumberInBoundsValidator(String message, T minBound, T maxBound) {
    this.message = message;
    this.minBound = minBound;
    this.maxBound = maxBound;
  }

  @Override
  public void validate(T input) {
    if (input.doubleValue() <= minBound.doubleValue() || input.doubleValue() >= maxBound.doubleValue()) {
      throw new NumberValueInvalidException(message);
    }
  }
}
