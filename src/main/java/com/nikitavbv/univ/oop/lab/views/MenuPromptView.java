package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuPromptView implements View {

  private final PrintStream out;
  private ResourceBundle resourceBundle;

  public MenuPromptView(OutputStream out, Locale locale) {
    this.out = new PrintStream(out);
    setLocale(locale);
  }

  public void printSelectMenuOptionPrompt() {
    out.println(resourceBundle.getString("prompt"));
  }

  @Override
  public void setLocale(Locale locale) {
    this.resourceBundle = ResourceBundle.getBundle("MenuPromptResources", locale);
  }
}
