package com.nikitavbv.univ.oop.lab.input;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class ApartmentSearchUserInput {
  private final Scanner scanner;

  public ApartmentSearchUserInput(InputStream in) {
    scanner = new Scanner(in);
  }

  public Optional<Integer> requestNumberOfRooms() {
    return readInt();
  }

  public Optional<Double> requestArea() {
    return readDouble();
  }

  public Optional<Integer> requestFloor() {
    return readInt();
  }

  private Optional<Integer> readInt() {
    String nextLine = scanner.nextLine();

    if (nextLine.matches("-?\\d+")) {
      return Optional.of(Integer.parseInt(nextLine));
    }

    return Optional.empty();
  }

  private Optional<Double> readDouble() {
    String nextLine = scanner.nextLine();

    if (nextLine.matches("-?\\d+(\\.\\d+)?")) {
      return Optional.of(Double.parseDouble(nextLine));
    }

    return Optional.empty();
  }
}
