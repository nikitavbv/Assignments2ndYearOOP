package com.nikitavbv.univ.oop.lab.providers.exceptions;

public class FailedToReadApartmentsException extends RuntimeException {

  public FailedToReadApartmentsException() {
    super("Failed to read apartments from file");
  }
}
