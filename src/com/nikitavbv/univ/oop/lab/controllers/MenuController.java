package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.MenuInput;
import com.nikitavbv.univ.oop.lab.models.MenuOption;
import com.nikitavbv.univ.oop.lab.views.MenuPromptView;
import com.nikitavbv.univ.oop.lab.views.MenuView;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("OptionalIsPresent")
public class MenuController {

  private MenuView menuView;
  private MenuPromptView menuPromptView;
  private MenuInput menuInput;

  private final Map<MenuOption, Runnable> handlers;

  public MenuController(MenuView menuView, MenuPromptView promptView, MenuInput menuInput, ApartmentController mainService) {
    this.menuView = menuView;
    this.menuPromptView = promptView;
    this.menuInput = menuInput;

    handlers = handlersInit(mainService);
  }

  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      menuView.showMenu();

      menuPromptView.printSelectMenuOptionPrompt();
      Optional<MenuOption> menuOption = menuInput.requestMenuOption();
      if (menuOption.isEmpty()) {
        menuView.showInvalidActionSelectedError();
        continue;
      }

      Optional<Runnable> handler = handlerByOption(menuOption.get());

      if (handler.isPresent()) {
        handler.get().run();
      }
    }
  }

  private Optional<Runnable> handlerByOption(MenuOption menuOption) {
    return Optional.ofNullable(handlers.get(menuOption));
  }

  private Map<MenuOption, Runnable> handlersInit(ApartmentController apartmentController) {
    return Map.of(
            MenuOption.SHOW_ALL, apartmentController::runShowAll,
            MenuOption.SEARCH_BY_ROOMS, apartmentController::runSearchByRooms,
            MenuOption.SEARCH_BY_AREA_AND_FLOOR, apartmentController::runSearchByAreaAndFloor,
            MenuOption.EXIT, () -> System.exit(0)
    );
  }
}
