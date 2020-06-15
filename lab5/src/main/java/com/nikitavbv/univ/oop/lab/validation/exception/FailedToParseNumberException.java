package com.nikitavbv.univ.oop.lab.validation.exception;

public class FailedToParseNumberException extends RuntimeException {

  public FailedToParseNumberException(String input) {
    super(String.format("failed to parse \"%s\" as a number.", input));
  }
}
