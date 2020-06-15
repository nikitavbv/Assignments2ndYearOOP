package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.services.MessagesService;
import java.io.OutputStream;
import java.io.PrintStream;

public class ApartmentSearchUserPromptView {

  private final PrintStream outputWriter;
  private final MessagesService messagesService;

  public ApartmentSearchUserPromptView(OutputStream outputStream, MessagesService messagesService) {
    this.outputWriter = new PrintStream(outputStream);
    this.messagesService = messagesService;
  }

  public void printNumberOfRoomsRequest() {
    printFilterCriteriaRequest(messagesService.getString("numberOfRoomsRequest"));
  }

  public void printAreaRequest() {
    printFilterCriteriaRequest(messagesService.getString("areaRequest"));
  }

  public void printFloorRequest() {
    printFilterCriteriaRequest(messagesService.getString("filterCriteriaRequest"));
  }

  private void printFilterCriteriaRequest(String criteria) {
    outputWriter.printf(messagesService.getString("filterCriteriaRequest"), criteria);
  }
}
