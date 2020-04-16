package com.nikitavbv.univ.oop.lab.input;

import com.nikitavbv.univ.oop.lab.models.BuildingType;
import com.nikitavbv.univ.oop.lab.validation.exception.FailedToParseNumberException;
import java.io.InputStream;
import java.util.Scanner;

public class AddApartmentInput {

  private final Scanner scanner;

  public AddApartmentInput(InputStream in) {
    this.scanner = new Scanner(in);
  }

  public int requestFlatNumber() {
    return readInt();
  }

  public double requestArea() {
    return readInt();
  }

  public int requestFloor() {
    return readInt();
  }

  public int requestTotalRooms() {
    return readInt();
  }

  public BuildingType requestBuildingType() {
    String nextLine = scanner.nextLine().toUpperCase().replaceAll("\n", "");
    return BuildingType.valueOf(nextLine);
  }

  public double requestLifetimeYears() {
    return readDouble();
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
