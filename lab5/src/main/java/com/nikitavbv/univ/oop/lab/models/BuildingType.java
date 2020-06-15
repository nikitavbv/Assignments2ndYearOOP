package com.nikitavbv.univ.oop.lab.models;

public enum BuildingType {
  DETACHED,
  SEMI_DETACHED,
  COTTAGE,
  TERRACE,
  FLAT_BLOCK;

  public int maxNumberOfFlats() {
    switch (this) {
      case DETACHED:
      case COTTAGE:
        return 1;
      case SEMI_DETACHED:
        return 2;
      case TERRACE:
        return 50;
      case FLAT_BLOCK:
        return 400;
      default:
        return 100;
    }
  }

  public double maxArea() {
    switch (this) {
      case DETACHED:
      case SEMI_DETACHED:
        return 300;
      case COTTAGE:
        return 250;
      case TERRACE:
        return 200;
      case FLAT_BLOCK:
        return 150;
      default:
        return 150;
    }
  }

  public int maxFloor() {
    switch (this) {
      case DETACHED:
      case SEMI_DETACHED:
      case TERRACE:
      case COTTAGE:
        return 1;
      case FLAT_BLOCK:
        return 35;
      default:
        return 20;
    }
  }

  public int maxTotalRooms() {
    switch (this) {
      case DETACHED:
        return 12;
      case SEMI_DETACHED:
        return 10;
      case TERRACE:
      case COTTAGE:
        return 8;
      case FLAT_BLOCK:
        return 6;
      default:
        return 6;
    }
  }

  public int maxLifetime() {
    return 20;
  }
}
