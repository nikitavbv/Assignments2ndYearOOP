package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuPromptView {

  private final PrintStream out;
  private final ResourceBundle resourceBundle;

  public MenuPromptView(OutputStream out, Locale locale) {
    this.out = new PrintStream(out);
    this.resourceBundle = ResourceBundle.getBundle("MenuPromptResources", locale);
  }

  public void printSelectMenuOptionPrompt() {
    out.println(resourceBundle.getString("prompt"));
  }
}
