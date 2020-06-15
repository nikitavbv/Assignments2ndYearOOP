package com.nikitavbv.univ.oop.lab.views;

import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.services.MessagesService;
import com.nikitavbv.univ.oop.lab.validation.exception.FailedToParseNumberException;
import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ApartmentsView {
  private static final long NOTHING_FOUND_DELAY = 100; // ms

  private final PrintStream outputWriter;
  private final PrintStream errorWriter;
  private final MessagesService messagesService;

  public ApartmentsView(OutputStream outputStream, OutputStream errorStream, MessagesService messagesService) {
    outputWriter = new PrintStream(outputStream);
    errorWriter = new PrintStream(errorStream);
    this.messagesService = messagesService;
  }

  public void showApartments(Apartment[] apartments) {
    outputWriter.println(messagesService.getString("tableHeader"));

    if (apartments.length == 0) {
      errorWriter.println(messagesService.getString("onNothingFoundMessage"));
      delayAfterNothingFound();
      return;
    }

    outputWriter.printf(
            "%10s | %10s | %10s | %10s | %20s | %10s%n",
            messagesService.getString("numberColumn"),
            messagesService.getString("areaColumn"),
            messagesService.getString("floorColumn"),
            messagesService.getString("roomsColumn"),
            messagesService.getString("typeColumn"),
            messagesService.getString("lifetimeColumn")
    );

    for (Apartment apartment : apartments) {
      outputWriter.println(showApartment(apartment));
    }
  }

  public void showFailedToSaveApartmentsErrorMessage() {
    showErrorMessage(messagesService.getString("failedToSaveApartments"));
  }

  public void showErrorMessage(FailedToParseNumberException e) {
    showErrorMessage(messagesService.getString("failedToParseNumberError") + e.getInput());
  }

  public void showErrorMessage(NumberValueInvalidException e) {
    showErrorMessage(messagesService.getString("invalidNumberValueError"));
  }

  private void showErrorMessage(String message) {
    errorWriter.println(messagesService.getString("errorMessage") + message);
  }

  public void notifyApartmentsSaved() {
    outputWriter.println(messagesService.getString("apartmentsSavedMessage"));
  }

  private String showApartment(Apartment apartment) {
    return String.format(
            "%10d | %10f | %10d | %10s | %20s | %10f",
            apartment.getFlatNumber(),
            apartment.getArea(),
            apartment.getFloor(),
            apartment.getTotalRooms(),
            messagesService.getString("type_" + apartment.getBuildingType().toString().toLowerCase()),
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
