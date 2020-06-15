package com.nikitavbv.univ.oop.lab.models;

import java.util.Map;
import java.util.Optional;

public enum MenuOption {

  SHOW_ALL,
  SEARCH_BY_ROOMS,
  SEARCH_BY_AREA_AND_FLOOR,
  EXIT;

  private static final Map<String, MenuOption> COMMANDS_TO_OPTIONS = Map.of(
          "all", SHOW_ALL,
          "search_rooms", SEARCH_BY_ROOMS,
          "search_area_floor", SEARCH_BY_AREA_AND_FLOOR,
          "exit", EXIT
  );

  private static final Map<MenuOption, String> OPTIONS_TO_DESCRIPTIONS = Map.of(
          SHOW_ALL, "Show all apartments",
          SEARCH_BY_ROOMS, "Search by rooms",
          SEARCH_BY_AREA_AND_FLOOR, "Search by area and floor",
          EXIT, "Exit"
  );

  public static Optional<MenuOption> byCommand(String command) {
    return Optional.ofNullable(COMMANDS_TO_OPTIONS.get(command));
  }

  public String description() {
    return String.format("[%s]: %s", command(), OPTIONS_TO_DESCRIPTIONS.get(this));
  }

  public String command() {
    for (String key : COMMANDS_TO_OPTIONS.keySet()) {
      if (COMMANDS_TO_OPTIONS.get(key) == this) {
        return key;
      }
    }

    return "";
  }
}
