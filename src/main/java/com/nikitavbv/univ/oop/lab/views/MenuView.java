package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.models.MenuOption;
import com.nikitavbv.univ.oop.lab.services.MessagesService;
import com.nikitavbv.univ.oop.lab.validation.exception.UnknownOptionException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuView {

  private final PrintStream outputWriter;
  private final PrintStream errorWriter;
  private final MessagesService messagesService;

  public MenuView(OutputStream outputStream, OutputStream errorWriter, MessagesService messagesService) {
    this.outputWriter = new PrintStream(outputStream);
    this.errorWriter = new PrintStream(errorWriter);
    this.messagesService = messagesService;
  }

  public void notifyLocaleChanged() {
    outputWriter.println(messagesService.getString("localeChangedNotif"));
  }

  public void showFailedToReadApartmentsError() {
    showError(messagesService.getString("failedToReadApartmentsError"));
  }

  public void showError(UnknownOptionException e) {
    showError(messagesService.getString("unknownOptionMessage") + e.getOption());
  }

  private void showError(String message) {
    errorWriter.println(messagesService.getString("errorMessage") + message);
  }

  public void showMenu() {
    outputWriter.println(messagesService.getString("menuIntroHeader"));
    outputWriter.println(messagesService.getString("menuIntroPossibleActions"));
    outputWriter.println(possibleActionsAsString());
  }

  public String possibleActionsAsString() {
    return Arrays.stream(MenuOption.values())
            .map(option -> String.format(
                    "[%s]: %s",
                    option.command(),
                    messagesService.getString("command_" + option.command())
            ))
            .collect(Collectors.joining(System.lineSeparator()));
  }
}
