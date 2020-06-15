package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;

public class ApartmentSearchUserPromptView {

  private PrintStream outputWriter;

  public ApartmentSearchUserPromptView(OutputStream outputStream) {
    this.outputWriter = new PrintStream(outputStream);
  }

  public void printNumberOfRoomsRequest() {
    printFilterCriteriaRequest("number of rooms");
  }

  public void printAreaRequest() {
    printFilterCriteriaRequest("area");
  }

  public void printFloorRequest() {
    printFilterCriteriaRequest("floor");
  }

  private void printFilterCriteriaRequest(String criteria) {
    outputWriter.printf("Please enter filter criteria (%s): ", criteria);
  }
}
