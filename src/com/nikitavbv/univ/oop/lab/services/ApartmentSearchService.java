package com.nikitavbv.univ.oop.lab.services;

import com.nikitavbv.univ.oop.lab.models.Apartment;
import java.util.Arrays;
import java.util.function.Predicate;

public class ApartmentSearchService {

  public static Predicate<Apartment> numberOfRoomsCriteria(int numberOfRooms) {
    return apartment -> apartment.getTotalRooms() == numberOfRooms;
  }

  public static Predicate<Apartment> minAreaCriteria(double minArea) {
    return apartment -> apartment.getArea() >= minArea;
  }

  public static Predicate<Apartment> minFloorCriteria(int minFloor) {
    return apartment -> apartment.getFloor() >= minFloor;
  }

  public Apartment[] apartmentsByCriteria(Apartment[] apartments, Predicate<Apartment> criteria) {
    // Simpler: return Arrays.stream(apartments).filter(criteria).toArray(Apartment[]::new);

    Apartment[] newArr = new Apartment[apartments.length];
    int i = 0;
    for (Apartment apartment : apartments) {
      if (criteria.test(apartment)) {
        newArr[i] = apartment;
        i++;
      }
    }

    Apartment[] trimmed = new Apartment[i];
    System.arraycopy(newArr, 0, trimmed, 0, i);

    return trimmed;
  }
}
