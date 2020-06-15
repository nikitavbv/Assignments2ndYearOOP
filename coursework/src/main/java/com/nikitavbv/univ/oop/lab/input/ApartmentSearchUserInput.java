package com.nikitavbv.univ.oop.lab.input;

import com.nikitavbv.univ.oop.lab.validation.exception.FailedToParseNumberException;
import java.io.InputStream;
import java.util.Scanner;

public class ApartmentSearchUserInput {
  private final Scanner scanner;

  public ApartmentSearchUserInput(InputStream in) {
    scanner = new Scanner(in);
  }

  public Integer requestNumberOfRooms() {
    return readInt();
  }

  public Double requestArea() {
    return readDouble();
  }

  public Integer requestFloor() {
    return readInt();
  }

  private Integer readInt() {
    String nextLine = scanner.nextLine();

    if (!nextLine.matches("-?\\d+")) {
      throw new FailedToParseNumberException(nextLine);
    }

    return Integer.parseInt(nextLine);
  }

  private Double readDouble() {
    String nextLine = scanner.nextLine();

    if (!nextLine.matches("-?\\d+(\\.\\d+)?")) {
      throw new FailedToParseNumberException(nextLine);
    }

    return Double.parseDouble(nextLine);
  }
}
