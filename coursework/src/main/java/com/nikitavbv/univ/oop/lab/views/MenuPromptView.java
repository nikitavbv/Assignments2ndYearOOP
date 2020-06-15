package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.services.MessagesService;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuPromptView {

  private final PrintStream out;
  private final MessagesService messagesService;

  public MenuPromptView(OutputStream out, MessagesService messagesService) {
    this.out = new PrintStream(out);
    this.messagesService = messagesService;
  }

  public void printSelectMenuOptionPrompt() {
    out.println(messagesService.getString("prompt"));
  }
}
