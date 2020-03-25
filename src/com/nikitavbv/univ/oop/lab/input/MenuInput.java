package com.nikitavbv.univ.oop.lab.input;

import com.nikitavbv.univ.oop.lab.models.MenuOption;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class MenuInput {

  private Scanner scanner;

  public MenuInput(InputStream inputStream) {
    this.scanner = new Scanner(inputStream);
  }

  public Optional<MenuOption> requestMenuOption() {
    return MenuOption.byCommand(scanner.nextLine().replaceAll("\n", "").trim());
  }
}
