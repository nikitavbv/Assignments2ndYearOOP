package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.MenuInput;
import com.nikitavbv.univ.oop.lab.models.MenuOption;
import com.nikitavbv.univ.oop.lab.validation.AllowedOptionValidator;
import com.nikitavbv.univ.oop.lab.validation.Validator;
import com.nikitavbv.univ.oop.lab.validation.exception.UnknownOptionException;
import com.nikitavbv.univ.oop.lab.views.MenuPromptView;
import com.nikitavbv.univ.oop.lab.views.MenuView;
import java.util.Map;
import java.util.Optional;

public class MenuController {
  private static final Validator<String> MENU_OPTION_VALIDATOR = new AllowedOptionValidator(new String[] {
          "all", "search_rooms", "search_area_floor", "exit"
  });

  private MenuView menuView;
  private MenuPromptView menuPromptView;
  private MenuInput menuInput;

  private final Map<MenuOption, MenuOptionHandler> handlers;

  public MenuController(MenuView menuView, MenuPromptView promptView, MenuInput menuInput, ApartmentController mainService) {
    this.menuView = menuView;
    this.menuPromptView = promptView;
    this.menuInput = menuInput;

    handlers = handlersInit(mainService);
  }

  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        menuView.showMenu();

        menuPromptView.printSelectMenuOptionPrompt();
        String menuOptionName = menuInput.requestMenuOptionName();
        MENU_OPTION_VALIDATOR.validate(menuOptionName);

        Optional<MenuOptionHandler> handler = handlerByOption(MenuOption.byCommand(menuOptionName));

        if (handler.isPresent()) {
          handler.get().handle();
        }
      } catch (UnknownOptionException e) {
        menuView.showError(e.getMessage());
      }
    }
  }

  private Optional<MenuOptionHandler> handlerByOption(MenuOption menuOption) {
    return Optional.ofNullable(handlers.get(menuOption));
  }

  private Map<MenuOption, MenuOptionHandler> handlersInit(ApartmentController apartmentController) {
    return Map.of(
            MenuOption.SHOW_ALL, apartmentController::runShowAll,
            MenuOption.SEARCH_BY_ROOMS, apartmentController::runSearchByRooms,
            MenuOption.SEARCH_BY_AREA_AND_FLOOR, apartmentController::runSearchByAreaAndFloor,
            MenuOption.EXIT, () -> System.exit(0)
    );
  }

  public interface MenuOptionHandler {
    void handle();
  }
}
