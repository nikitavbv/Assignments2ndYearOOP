package com.nikitavbv.univ.oop.lab.input;

import java.io.InputStream;
import java.util.Scanner;

public class MenuInput {

  private Scanner scanner;

  public MenuInput(InputStream inputStream) {
    this.scanner = new Scanner(inputStream);
  }

  public String requestMenuOptionName() {
    return scanner.nextLine().replaceAll("\n", "").trim();
  }
}
