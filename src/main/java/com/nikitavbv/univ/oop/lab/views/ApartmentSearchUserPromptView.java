package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApartmentSearchUserPromptView {

  private final PrintStream outputWriter;
  private final ResourceBundle resourceBundle;

  public ApartmentSearchUserPromptView(OutputStream outputStream, Locale locale) {
    this.outputWriter = new PrintStream(outputStream);
    this.resourceBundle = ResourceBundle.getBundle("ApartmentSearchResources", locale);
  }

  public void printNumberOfRoomsRequest() {
    printFilterCriteriaRequest(resourceBundle.getString("numberOfRoomsRequest"));
  }

  public void printAreaRequest() {
    printFilterCriteriaRequest(resourceBundle.getString("areaRequest"));
  }

  public void printFloorRequest() {
    printFilterCriteriaRequest(resourceBundle.getString("filterCriteriaRequest"));
  }

  private void printFilterCriteriaRequest(String criteria) {
    outputWriter.printf(resourceBundle.getString("filterCriteriaRequest"), criteria);
  }
}
