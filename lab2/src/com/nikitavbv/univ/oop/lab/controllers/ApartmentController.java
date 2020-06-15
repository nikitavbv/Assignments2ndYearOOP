package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.ApartmentSearchUserInput;
import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.services.ApartmentSearchService;
import com.nikitavbv.univ.oop.lab.validation.NonNegativeNumberValidator;
import com.nikitavbv.univ.oop.lab.validation.NumberInBoundsValidator;
import com.nikitavbv.univ.oop.lab.validation.Validator;
import com.nikitavbv.univ.oop.lab.validation.exception.FailedToParseNumberException;
import com.nikitavbv.univ.oop.lab.validation.exception.NumberValueInvalidException;
import com.nikitavbv.univ.oop.lab.views.ApartmentSearchUserPromptView;
import com.nikitavbv.univ.oop.lab.views.ApartmentsView;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class ApartmentController {

  private static final Validator<Integer> NUMBER_OF_ROOMS_VALIDATOR =
          new NonNegativeNumberValidator<>("Number of rooms should be a positive number");
  private static final Validator<Double> AREA_VALIDATOR =
          new NonNegativeNumberValidator<>("Area should be a positive number");
  private static final Validator<Integer> FLOOR_VALIDATOR =
          new NumberInBoundsValidator<>("Floor should be in (0..100)", 0, 100);

  private ApartmentsView apartmentsView;
  private ApartmentSearchService apartmentSearchService;
  private ApartmentSearchUserPromptView apartmentSearchUserPromptView;
  private ApartmentSearchUserInput apartmentSearchUserInput;

  public ApartmentController(ApartmentSearchService apartmentSearchService,
                             ApartmentsView apartmentsView,
                             ApartmentSearchUserPromptView apartmentSearchUserPromptView,
                             ApartmentSearchUserInput apartmentSearchUserInput) {
    this.apartmentsView = apartmentsView;
    this.apartmentSearchService = apartmentSearchService;
    this.apartmentSearchUserPromptView = apartmentSearchUserPromptView;
    this.apartmentSearchUserInput = apartmentSearchUserInput;
  }

  public void runShowAll() {
    apartmentsView.showApartments(apartmentSearchService.allApartments());
  }

  public void runSearchByRooms() {
    try {
      apartmentSearchUserPromptView.printNumberOfRoomsRequest();

      Integer numberOfRoomsFilter = apartmentSearchUserInput.requestNumberOfRooms();
      NUMBER_OF_ROOMS_VALIDATOR.validate(numberOfRoomsFilter);

      Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
              ApartmentSearchService.numberOfRoomsCriteria(numberOfRoomsFilter)
      );

      apartmentsView.showApartments(searchResults);
    } catch (FailedToParseNumberException | NumberValueInvalidException e) {
      apartmentsView.showErrorMessage(e.getMessage());
    }
  }

  public void runSearchByAreaAndFloor() {
    try {
      apartmentSearchUserPromptView.printAreaRequest();
      Double minArea = apartmentSearchUserInput.requestArea();
      AREA_VALIDATOR.validate(minArea);

      apartmentSearchUserPromptView.printFloorRequest();
      Integer minFloor = apartmentSearchUserInput.requestFloor();
      FLOOR_VALIDATOR.validate(minFloor);

      Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
              ApartmentSearchService.minAreaCriteria(minArea).and(ApartmentSearchService.minFloorCriteria(minFloor))
      );
      apartmentsView.showApartments(searchResults);
    } catch (FailedToParseNumberException | NumberValueInvalidException e) {
      apartmentsView.showErrorMessage(e.getMessage());
    }
  }
}
