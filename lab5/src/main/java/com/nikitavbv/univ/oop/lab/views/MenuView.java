package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.models.MenuOption;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MenuView {

  private PrintStream outputWriter;
  private PrintStream errorWriter;

  public MenuView(OutputStream outputStream, OutputStream errorWriter) {
    this.outputWriter = new PrintStream(outputStream);
    this.errorWriter = new PrintStream(errorWriter);
  }

  public void showError(String message) {
    errorWriter.println("error: " + message);
  }

  public void showMenu() {
    outputWriter.println(
            "Menu\n" +
            "Possible actions:\n" +
            possibleActionsAsString()
    );
  }

  public String possibleActionsAsString() {
    return Arrays.stream(MenuOption.values())
            .map(MenuOption::description)
            .collect(Collectors.joining(System.lineSeparator()));
  }
}
