package com.nikitavbv.univ.oop.lab.validation.exception;

public class UnknownOptionException extends RuntimeException {

  public UnknownOptionException(String option) {
    super("unknown option: " + option);
  }
}
