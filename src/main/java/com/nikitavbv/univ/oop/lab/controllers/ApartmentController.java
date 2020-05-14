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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class ApartmentController {
  private static final Logger LOGGER = LogManager.getLogger();

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
    LOGGER.info("running add new apartment flow");

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

    LOGGER.info("new apartment is added");
  }

  public void runShowAll() {
    LOGGER.info("running show all apartments flow");
    apartmentsView.showApartments(apartmentSearchService.allApartments());
  }

  public void runSearchByRooms() {
    LOGGER.info("running search by rooms flow");

    apartmentSearchUserPromptView.printNumberOfRoomsRequest();
    Integer numberOfRoomsFilter;

    while(true) {
      try {
        numberOfRoomsFilter = apartmentSearchUserInput.requestNumberOfRooms();
        NUMBER_OF_ROOMS_VALIDATOR.validate(numberOfRoomsFilter);
        break;
      } catch (FailedToParseNumberException e) {
        LOGGER.warn("failed to parse search input", e);
        apartmentsView.showErrorMessage(e);
      } catch (NumberValueInvalidException e) {
        LOGGER.warn("invalid number value", e);
        apartmentsView.showErrorMessage(e);
      }
    }

    LOGGER.info("searching apartments with rooms >= " + numberOfRoomsFilter);

    Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
      ApartmentSearchService.numberOfRoomsCriteria(numberOfRoomsFilter)
    );

    LOGGER.info("total apartments found: " + searchResults.length);

    apartmentsView.showApartments(searchResults);
  }

  public void runSearchByAreaAndFloor() {
    LOGGER.info("running search by area and floor flow");

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
      } catch (FailedToParseNumberException e) {
        LOGGER.warn("failed to parse search input", e);
        apartmentsView.showErrorMessage(e);
      } catch (NumberValueInvalidException e) {
        LOGGER.warn("invalid number value", e);
        apartmentsView.showErrorMessage(e);
      }
    }

    LOGGER.info("running search with area >= " + minArea + " and floor >= " + minFloor);

    Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
      ApartmentSearchService.minAreaCriteria(minArea).and(ApartmentSearchService.minFloorCriteria(minFloor))
    );

    LOGGER.info("total apartments found: " + searchResults.length);

    apartmentsView.showApartments(searchResults);
  }

  public void saveApartments() {
    try {
      LOGGER.info("saving apartments");

      apartmentWriter.saveApartments(apartmentSearchService.allApartments());
      apartmentsView.notifyApartmentsSaved();

      LOGGER.info("saved apartments to file");
    } catch (IOException e) {
      LOGGER.error("failed to save apartments to file", e);
      apartmentsView.showFailedToSaveApartmentsErrorMessage();
    }
  }
}
