package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddApartmentView {

  private final PrintStream out;
  private final ResourceBundle resourceBundle;

  public AddApartmentView(OutputStream out, Locale locale) {
    this.out = new PrintStream(out);
    this.resourceBundle = ResourceBundle.getBundle("AddApartmentResources", locale);
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
    out.println(resourceBundle.getString(key));
  }
}
