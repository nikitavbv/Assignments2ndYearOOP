package com.nikitavbv.univ.oop.lab.services;

import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.providers.ApartmentProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;

public class ApartmentSearchService {

  private ApartmentProvider apartmentProvider;

  public ApartmentSearchService(ApartmentProvider apartmentProvider) {
    this.apartmentProvider = apartmentProvider;
  }

  public static Predicate<Apartment> numberOfRoomsCriteria(int numberOfRooms) {
    return apartment -> apartment.getTotalRooms() == numberOfRooms;
  }

  public static Predicate<Apartment> minAreaCriteria(double minArea) {
    return apartment -> apartment.getArea() >= minArea;
  }

  public static Predicate<Apartment> minFloorCriteria(int minFloor) {
    return apartment -> apartment.getFloor() >= minFloor;
  }

  public Apartment[] allApartments() {
    return apartmentProvider.allApartments();
  }

  public Apartment[] apartmentsByCriteria(Predicate<Apartment> criteria) {
    // Simpler: return Arrays.stream(apartments).filter(criteria).toArray(Apartment[]::new);

    Apartment[] apartments = apartmentProvider.allApartments();
    Apartment[] newArr = new Apartment[apartments.length];
    int i = 0;
    for (Apartment apartment : apartments) {
      if (criteria.test(apartment)) {
        newArr[i] = apartment;
        i++;
      }
    }

    return Arrays.copyOf(newArr, i);
  }
}
