package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;

public class MenuPromptView {

  private static final String INPUT_MENU_OPTION_PROMPT = "Select menu option: ";

  private final PrintStream out;

  public MenuPromptView(OutputStream out) {
    this.out = new PrintStream(out);
  }

  public void printSelectMenuOptionPrompt() {
    out.println(INPUT_MENU_OPTION_PROMPT);
  }
}
