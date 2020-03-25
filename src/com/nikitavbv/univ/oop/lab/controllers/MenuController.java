package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.MenuInput;
import com.nikitavbv.univ.oop.lab.models.MenuOption;
import com.nikitavbv.univ.oop.lab.views.MenuPromptView;
import com.nikitavbv.univ.oop.lab.views.MenuView;
import java.util.Map;
import java.util.Optional;

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
    menuView.showWelcome();

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
      } else {
        throw new AssertionError("Runnable for option not set: " + menuOption);
      }
    }
  }

  private Optional<Runnable> handlerByOption(MenuOption menuOption) {
    return Optional.ofNullable(handlers.get(menuOption));
  }

  private Map<MenuOption, Runnable> handlersInit(ApartmentController mainService) {
    return Map.of(
            MenuOption.SHOW_ALL, mainService::runShowAll,
            MenuOption.SEARCH_BY_ROOMS, mainService::runSearchByRooms,
            MenuOption.SEARCH_BY_AREA_AND_FLOOR, mainService::runSearchByAreaAndFloor,
            MenuOption.EXIT, () -> System.exit(0)
    );
  }
}
