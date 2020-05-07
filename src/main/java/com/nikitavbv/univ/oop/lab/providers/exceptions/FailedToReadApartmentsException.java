package com.nikitavbv.univ.oop.lab.providers.exceptions;

import java.io.IOException;

public class FailedToReadApartmentsException extends RuntimeException {

  public FailedToReadApartmentsException(Exception e) {
    super("Failed to read apartments from file", e);
  }
}
