package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.validation.exception.FailedToParseNumberException;
import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class ApartmentsView {
  private static final long NOTHING_FOUND_DELAY = 100; // ms

  private final PrintStream outputWriter;
  private final PrintStream errorWriter;
  private final ResourceBundle resourceBundle;

  public ApartmentsView(OutputStream outputStream, OutputStream errorStream, Locale locale) {
    outputWriter = new PrintStream(outputStream);
    errorWriter = new PrintStream(errorStream);
    resourceBundle = ResourceBundle.getBundle("ApartmentsViewResources", locale);
  }

  public void showApartments(Apartment[] apartments) {
    outputWriter.println(resourceBundle.getString("tableHeader"));

    if (apartments.length == 0) {
      errorWriter.println(resourceBundle.getString("onNothingFoundMessage"));
      delayAfterNothingFound();
      return;
    }

    outputWriter.printf(
            "%10s | %10s | %10s | %10s | %20s | %10s%n",
            resourceBundle.getString("numberColumn"),
            resourceBundle.getString("areaColumn"),
            resourceBundle.getString("floorColumn"),
            resourceBundle.getString("roomsColumn"),
            resourceBundle.getString("typeColumn"),
            resourceBundle.getString("lifetimeColumn")
    );

    for (Apartment apartment : apartments) {
      outputWriter.println(showApartment(apartment));
    }
  }

  public void showFailedToSaveApartmentsErrorMessage() {
    showErrorMessage(resourceBundle.getString("failedToSaveApartments"));
  }

  public void showErrorMessage(FailedToParseNumberException e) {
    showErrorMessage(resourceBundle.getString("failedToParseNumberError") + e.getInput());
  }

  public void showErrorMessage(NumberValueInvalidException e) {
    showErrorMessage(resourceBundle.getString("invalidNumberValueError"));
  }

  private void showErrorMessage(String message) {
    errorWriter.println(resourceBundle.getString("errorMessage") + message);
  }

  public void notifyApartmentsSaved() {
    outputWriter.println(resourceBundle.getString("apartmentsSavedMessage"));
  }

  private String showApartment(Apartment apartment) {
    return String.format(
            "%10d | %10f | %10d | %10s | %20s | %10f",
            apartment.getFlatNumber(),
            apartment.getArea(),
            apartment.getFloor(),
            apartment.getTotalRooms(),
            resourceBundle.getString("type_" + apartment.getBuildingType().toString().toLowerCase()),
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
