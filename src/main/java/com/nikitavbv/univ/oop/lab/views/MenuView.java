package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.models.MenuOption;
import com.nikitavbv.univ.oop.lab.validation.exception.UnknownOptionException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuView {

  private PrintStream outputWriter;
  private PrintStream errorWriter;
  private ResourceBundle resourceBundle;

  public MenuView(OutputStream outputStream, OutputStream errorWriter, Locale locale) {
    this.outputWriter = new PrintStream(outputStream);
    this.errorWriter = new PrintStream(errorWriter);
    this.resourceBundle = ResourceBundle.getBundle("MenuResources", locale);
  }

  public void showFailedToReadApartmentsError() {
    showError(resourceBundle.getString("failedToReadApartmentsError"));
  }

  public void showError(UnknownOptionException e) {
    showError(resourceBundle.getString("unknownOptionMessage") + e.getOption());
  }

  private void showError(String message) {
    errorWriter.println(resourceBundle.getString("errorMessage") + message);
  }

  public void showMenu() {
    outputWriter.println(resourceBundle.getString("menuIntroHeader"));
    outputWriter.println(resourceBundle.getString("menuIntroPossibleActions"));
    outputWriter.println(possibleActionsAsString());
  }

  public String possibleActionsAsString() {
    return Arrays.stream(MenuOption.values())
            .map(option -> String.format(
                    "[%s]: %s",
                    option.command(),
                    resourceBundle.getString("command_" + option.command())
            ))
            .collect(Collectors.joining(System.lineSeparator()));
  }
}
