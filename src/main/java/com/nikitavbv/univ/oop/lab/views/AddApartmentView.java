package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.services.MessagesService;
import java.io.OutputStream;
import java.io.PrintStream;

public class AddApartmentView {

  private final PrintStream out;
  private final MessagesService messagesService;

  public AddApartmentView(OutputStream out, MessagesService messagesService) {
    this.out = new PrintStream(out);
    this.messagesService = messagesService;
  }

  public void printFlatNumberRequest() {
    printLocalized("flatNumberRequest");
  }

  public void printPrintAreaRequest() {
    printLocalized("areaRequest");
  }

  public void printFloorRequest() {
    printLocalized("floorRequest");
  }

  public void printTotalRoomsRequest() {
    printLocalized("totalRoomsRequest");
  }

  public void printBuildingTypeRequest() {
    printLocalized("buildingTypeRequest");
  }

  public void printLifetimeRequest() {
    printLocalized("lifetimeRequest");
  }

  private void printLocalized(String key) {
    out.println(messagesService.getString(key));
  }
}
