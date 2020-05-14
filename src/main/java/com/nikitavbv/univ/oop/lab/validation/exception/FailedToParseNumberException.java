package com.nikitavbv.univ.oop.lab.validation.exception;

public class FailedToParseNumberException extends RuntimeException {

  private final String input;

  public FailedToParseNumberException(String input) {
    super(String.format("failed to parse \"%s\" as a number.", input));
    this.input = input;
  }

  public String getInput() {
    return input;
  }
}
