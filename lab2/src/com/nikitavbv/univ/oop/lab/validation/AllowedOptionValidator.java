package com.nikitavbv.univ.oop.lab.validation;

import com.nikitavbv.univ.oop.lab.validation.exception.UnknownOptionException;
import java.util.Arrays;

public class AllowedOptionValidator implements Validator<String> {
  private final String[] allowedOptions;

  public AllowedOptionValidator(String[] allowedOptions) {
    this.allowedOptions = allowedOptions;
    Arrays.sort(allowedOptions);
  }

  public void validate(String command) {
    if (Arrays.binarySearch(allowedOptions, command) < 0) {
      throw new UnknownOptionException(command);
    }
  }
}
