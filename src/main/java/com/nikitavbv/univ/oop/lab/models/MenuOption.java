package com.nikitavbv.univ.oop.lab.models;

import java.util.Map;

public enum MenuOption {

  SHOW_ALL,
  SEARCH_BY_ROOMS,
  SEARCH_BY_AREA_AND_FLOOR,
  ADD_APARTMENT,
  EXIT;

  private static final Map<String, MenuOption> COMMANDS_TO_OPTIONS = Map.of(
          "all", SHOW_ALL,
          "search_rooms", SEARCH_BY_ROOMS,
          "search_area_floor", SEARCH_BY_AREA_AND_FLOOR,
          "add", ADD_APARTMENT,
          "exit", EXIT
  );

  public static MenuOption byCommand(String command) {
    return COMMANDS_TO_OPTIONS.get(command);
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
