package com.nikitavbv.univ.oop.lab.input;

import java.io.InputStream;
import java.util.Scanner;

public class ApartmentSearchUserInput {

  private final Scanner scanner;

  public ApartmentSearchUserInput(InputStream in) {
    scanner = new Scanner(in);
  }

  public int requestNumberOfRooms() {
    return readInt();
  }

  public double requestArea() {
    return readDouble();
  }

  public int requestFloor() {
    return readInt();
  }

  private int readInt() {
    return Integer.parseInt(scanner.nextLine());
  }

  private double readDouble() {
    return Double.parseDouble(scanner.nextLine());
  }
}
