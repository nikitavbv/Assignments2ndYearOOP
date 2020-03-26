package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.models.Apartment;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ApartmentsView {
  private static final long NOTHING_FOUND_DELAY = 100; // ms

  private static final String DEFAULT_APARTMENTS_TABLE_HEADER = "Apartments";
  private static final String DEFAULT_ON_NOTHING_FOUND_MESSAGE = "Oops, nothing here...";

  private PrintStream outputWriter;
  private PrintStream errorWriter;

  public ApartmentsView(OutputStream outputStream, OutputStream errorStream) {
    outputWriter = new PrintStream(outputStream);
    errorWriter = new PrintStream(errorStream);
  }

  public void showApartments(Apartment[] apartments) {
    outputWriter.println(DEFAULT_APARTMENTS_TABLE_HEADER);

    if (apartments.length == 0) {
      errorWriter.println(DEFAULT_ON_NOTHING_FOUND_MESSAGE);
      delayAfterNothingFound();
      return;
    }

    outputWriter.printf(
            "%10s | %10s | %10s | %10s | %20s | %10s%n",
            "number",
            "area",
            "floor",
            "rooms",
            "type",
            "lifetime"
    );

    for (Apartment apartment : apartments) {
      outputWriter.println(showApartment(apartment));
    }
  }

  public void showInvalidInputMessage(String message) {
    System.err.println("Invalid input: " + message);
  }

  private String showApartment(Apartment apartment) {
    return String.format(
            "%10d | %10f | %10d | %10s | %20s | %10f",
            apartment.getFlatNumber(),
            apartment.getArea(),
            apartment.getFloor(),
            apartment.getTotalRooms(),
            apartment.getBuildingType(),
            apartment.getLifetimeYears()
    );
  }

  private void delayAfterNothingFound() {
    try {
      Thread.sleep(NOTHING_FOUND_DELAY);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
