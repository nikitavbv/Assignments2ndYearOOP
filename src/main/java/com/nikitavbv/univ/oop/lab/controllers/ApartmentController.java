package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.AddApartmentInput;
import com.nikitavbv.univ.oop.lab.input.ApartmentSearchUserInput;
import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.models.BuildingType;
import com.nikitavbv.univ.oop.lab.models.persistence.FileApartmentWriter;
import com.nikitavbv.univ.oop.lab.providers.ApartmentProvider;
import com.nikitavbv.univ.oop.lab.services.ApartmentSearchService;
import com.nikitavbv.univ.oop.lab.validation.NonNegativeNumberValidator;
import com.nikitavbv.univ.oop.lab.validation.NumberInBoundsValidator;
import com.nikitavbv.univ.oop.lab.validation.Validator;
import com.nikitavbv.univ.oop.lab.validation.exception.FailedToParseNumberException;
import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;
import com.nikitavbv.univ.oop.lab.views.AddApartmentView;
import com.nikitavbv.univ.oop.lab.views.ApartmentSearchUserPromptView;
import com.nikitavbv.univ.oop.lab.views.ApartmentsView;
import java.io.IOException;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class ApartmentController {

  private static final Validator<Integer> NUMBER_OF_ROOMS_VALIDATOR =
          new NonNegativeNumberValidator<>("Number of rooms should be a positive number");
  private static final Validator<Double> AREA_VALIDATOR =
          new NonNegativeNumberValidator<>("Area should be a positive number");
  private static final Validator<Integer> FLOOR_VALIDATOR =
          new NumberInBoundsValidator<>("Floor should be in (0..100)", 0, 100);

  private final ApartmentsView apartmentsView;
  private final ApartmentSearchService apartmentSearchService;
  private final ApartmentSearchUserPromptView apartmentSearchUserPromptView;
  private final ApartmentSearchUserInput apartmentSearchUserInput;
  private final FileApartmentWriter apartmentWriter;
  private final AddApartmentView addApartmentView;
  private final AddApartmentInput addApartmentInput;
  private final ApartmentProvider apartmentProvider;

  public ApartmentController(ApartmentSearchService apartmentSearchService,
                             ApartmentsView apartmentsView,
                             ApartmentSearchUserPromptView apartmentSearchUserPromptView,
                             ApartmentSearchUserInput apartmentSearchUserInput,
                             FileApartmentWriter apartmentWriter,
                             AddApartmentView addApartmentView,
                             AddApartmentInput addApartmentInput,
                             ApartmentProvider apartmentProvider) {
    this.apartmentsView = apartmentsView;
    this.apartmentSearchService = apartmentSearchService;
    this.apartmentSearchUserPromptView = apartmentSearchUserPromptView;
    this.apartmentSearchUserInput = apartmentSearchUserInput;
    this.apartmentWriter = apartmentWriter;
    this.addApartmentView = addApartmentView;
    this.addApartmentInput = addApartmentInput;
    this.apartmentProvider = apartmentProvider;
  }

  public void addApartment() {
    System.out.println("here");

    addApartmentView.printFlatNumberRequest();
    int flatNumber = addApartmentInput.requestFlatNumber();

    addApartmentView.printPrintAreaRequest();
    double area = addApartmentInput.requestArea();

    addApartmentView.printFloorRequest();
    int floor = addApartmentInput.requestFloor();

    addApartmentView.printTotalRoomsRequest();
    int totalRooms = addApartmentInput.requestTotalRooms();

    addApartmentView.printBuildingTypeRequest();
    BuildingType buildingType = addApartmentInput.requestBuildingType();

    addApartmentView.printLifetimeRequest();
    double lifetime = addApartmentInput.requestLifetimeYears();

    Apartment apartment = new Apartment(flatNumber, area, floor, totalRooms, buildingType, lifetime);
    apartmentProvider.addNew(apartment);
  }

  public void runShowAll() {
    apartmentsView.showApartments(apartmentSearchService.allApartments());
  }

  public void runSearchByRooms() {
    apartmentSearchUserPromptView.printNumberOfRoomsRequest();
    Integer numberOfRoomsFilter;

      while(true) {
        try {
          numberOfRoomsFilter = apartmentSearchUserInput.requestNumberOfRooms();
          NUMBER_OF_ROOMS_VALIDATOR.validate(numberOfRoomsFilter);
          break;
        } catch (FailedToParseNumberException | NumberValueInvalidException e) {
          apartmentsView.showErrorMessage(e.getMessage());
        }
      }

      Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
              ApartmentSearchService.numberOfRoomsCriteria(numberOfRoomsFilter)
      );

      apartmentsView.showApartments(searchResults);
  }

  public void runSearchByAreaAndFloor() {
    Double minArea;
    Integer minFloor;

    while(true) {
      try {
        apartmentSearchUserPromptView.printAreaRequest();
        minArea = apartmentSearchUserInput.requestArea();
        AREA_VALIDATOR.validate(minArea);

        apartmentSearchUserPromptView.printFloorRequest();
        minFloor = apartmentSearchUserInput.requestFloor();
        FLOOR_VALIDATOR.validate(minFloor);

        break;
      } catch (FailedToParseNumberException | NumberValueInvalidException e) {
        apartmentsView.showErrorMessage(e.getMessage());
      }
    }

    Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
      ApartmentSearchService.minAreaCriteria(minArea).and(ApartmentSearchService.minFloorCriteria(minFloor))
    );
    apartmentsView.showApartments(searchResults);
  }

  public void saveApartments() {
    try {
      apartmentWriter.saveApartments(apartmentSearchService.allApartments());
      apartmentsView.notifyApartmentsSaved();
    } catch (IOException e) {
      apartmentsView.showErrorMessage("failed to save apartments");
    }
  }
}
