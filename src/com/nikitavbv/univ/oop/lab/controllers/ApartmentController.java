package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.ApartmentSearchUserInput;
import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.providers.ApartmentProvider;
import com.nikitavbv.univ.oop.lab.services.ApartmentSearchService;
import com.nikitavbv.univ.oop.lab.validation.NonNegativeNumberValidator;
import com.nikitavbv.univ.oop.lab.validation.NumberInBoundsValidator;
import com.nikitavbv.univ.oop.lab.validation.Validator;
import com.nikitavbv.univ.oop.lab.validation.Verdict;
import com.nikitavbv.univ.oop.lab.views.ApartmentSearchUserPromptView;
import com.nikitavbv.univ.oop.lab.views.ApartmentsView;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class ApartmentController {

  private static final Validator<Integer> NUMBER_OF_ROOMS_VALIDATOR =
          new NonNegativeNumberValidator<>("Number of rooms should be a positive number");
  private static final Validator<Double> AREA_VALIDATOR =
          new NonNegativeNumberValidator<>("Area should be a positive number");
  private static final Validator<Integer> FLOOR_VALIDATOR =
          new NumberInBoundsValidator<>("Floor should be in (0..100)", 0, 100);

  private static final String EXPECTED_NUMBER_ERROR = "Input should be a number";

  private ApartmentsView apartmentsView;
  private ApartmentSearchService apartmentSearchService;
  private ApartmentSearchUserPromptView apartmentSearchUserPromptView;
  private ApartmentSearchUserInput apartmentSearchUserInput;

  private Apartment[] apartments;

  public ApartmentController(ApartmentProvider apartmentProvider,
                             ApartmentSearchService apartmentSearchService,
                             ApartmentsView apartmentsView,
                             ApartmentSearchUserPromptView apartmentSearchUserPromptView,
                             ApartmentSearchUserInput apartmentSearchUserInput) {
    this.apartmentsView = apartmentsView;
    this.apartmentSearchService = apartmentSearchService;
    this.apartmentSearchUserPromptView = apartmentSearchUserPromptView;
    this.apartmentSearchUserInput = apartmentSearchUserInput;

    this.apartments = apartmentProvider.allApartments();
  }

  public void runShowAll() {
    apartmentsView.showApartments(apartments);
  }

  public void runSearchByRooms() {
    apartmentSearchUserPromptView.printNumberOfRoomsRequest();

    Optional<Integer> numberOfRoomsFilter = apartmentSearchUserInput.requestNumberOfRooms();
    if (numberOfRoomsFilter.isEmpty()) {
      apartmentsView.showInvalidInputMessage(EXPECTED_NUMBER_ERROR);
      return;
    }

    Verdict verdict = NUMBER_OF_ROOMS_VALIDATOR.validate(numberOfRoomsFilter.get());
    if (verdict.isNegative()) {
      apartmentsView.showInvalidInputMessage(verdict.explanation().get());
      return;
    }

    Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
            apartments,
            ApartmentSearchService.numberOfRoomsCriteria(numberOfRoomsFilter.get())
    );

    apartmentsView.showApartments(searchResults);
  }

  public void runSearchByAreaAndFloor() {
    apartmentSearchUserPromptView.printAreaRequest();
    Optional<Double> minArea = apartmentSearchUserInput.requestArea();
    if (minArea.isEmpty()) {
      apartmentsView.showInvalidInputMessage(EXPECTED_NUMBER_ERROR);
      return;
    }
    Verdict minAreaValidationVerdict = AREA_VALIDATOR.validate(minArea.get());
    if (minAreaValidationVerdict.isNegative()) {
      apartmentsView.showInvalidInputMessage(minAreaValidationVerdict.explanation().get());
      return;
    }

    apartmentSearchUserPromptView.printFloorRequest();
    Optional<Integer> minFloor = apartmentSearchUserInput.requestFloor();
    if (minFloor.isEmpty()) {
      apartmentsView.showInvalidInputMessage(EXPECTED_NUMBER_ERROR);
      return;
    }
    Verdict minFloorValidationVerdict = FLOOR_VALIDATOR.validate(minFloor.get());
    if (minFloorValidationVerdict.isNegative()) {
      apartmentsView.showInvalidInputMessage(minFloorValidationVerdict.explanation().get());
      return;
    }

    Apartment[] searchResults = apartmentSearchService.apartmentsByCriteria(
            apartments,
            ApartmentSearchService.minAreaCriteria(minArea.get()).and(ApartmentSearchService.minFloorCriteria(minFloor.get()))
    );
    apartmentsView.showApartments(searchResults);
  }
}
