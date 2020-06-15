package com.nikitavbv.univ.oop.lab.validation.exception;

public class UnknownOptionException extends RuntimeException {

  private final String option;

  public UnknownOptionException(String option) {
    super("unknown option: " + option);
    this.option = option;
  }

  public String getOption() {
    return option;
  }
}
