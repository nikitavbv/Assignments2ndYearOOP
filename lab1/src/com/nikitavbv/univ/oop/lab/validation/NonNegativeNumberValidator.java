package com.nikitavbv.univ.oop.lab.validation;

public class NonNegativeNumberValidator<T extends Number> implements Validator<T> {

  private final String message;

  public NonNegativeNumberValidator(String message) {
    this.message = message;
  }

  @Override
  public Verdict validate(T input) {
    if (input.doubleValue() <= 0) {
      return Verdict.negative(message);
    }

    return Verdict.positive();
  }
}
