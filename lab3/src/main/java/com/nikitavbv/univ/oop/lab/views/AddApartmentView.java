package com.nikitavbv.univ.oop.lab.views;

import java.io.OutputStream;
import java.io.PrintStream;

public class AddApartmentView {

  private final PrintStream out;

  public AddApartmentView(OutputStream out) {
    this.out = new PrintStream(out);
  }

  public void printFlatNumberRequest() {
    out.println("flat number: ");
  }

  public void printPrintAreaRequest() {
    out.println("area: ");
  }

  public void printFloorRequest() {
    out.println("floor: ");
  }

  public void printTotalRoomsRequest() {
    out.println("total rooms: ");
  }

  public void printBuildingTypeRequest() {
    out.println("building type: ");
  }

  public void printLifetimeRequest() {
    out.println("lifetime: ");
  }
}
