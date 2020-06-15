package com.nikitavbv.univ.oop.lab.validation.exception;

public class NumberValueInvalidException extends RuntimeException {

  public NumberValueInvalidException(String message) {
    super("invalid number value: " + message);
  }
}
