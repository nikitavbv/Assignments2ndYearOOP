package com.nikitavbv.univ.oop.lab.controllers;

import com.nikitavbv.univ.oop.lab.input.MenuInput;
import com.nikitavbv.univ.oop.lab.models.MenuOption;
import com.nikitavbv.univ.oop.lab.providers.ApartmentProvider;
import com.nikitavbv.univ.oop.lab.providers.exceptions.FailedToReadApartmentsException;
import com.nikitavbv.univ.oop.lab.validation.AllowedOptionValidator;
import com.nikitavbv.univ.oop.lab.validation.Validator;
import com.nikitavbv.univ.oop.lab.validation.exception.UnknownOptionException;
import com.nikitavbv.univ.oop.lab.views.MenuPromptView;
import com.nikitavbv.univ.oop.lab.views.MenuView;
import com.nikitavbv.univ.oop.lab.views.View;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MenuController {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final Locale ENGLISH_LOCALE = new Locale("en", "US");
  private static final Locale RUSSIAN_LOCALE = new Locale("ru", "RU");

  private static final Validator<String> MENU_OPTION_VALIDATOR = new AllowedOptionValidator(new String[] {
          "all", "add", "search_rooms", "search_area_floor", "locale", "exit"
  });

  private final MenuView menuView;
  private final MenuPromptView menuPromptView;
  private final MenuInput menuInput;
  private final ApartmentProvider apartmentProvider;
  private final List<View> views;

  private final Map<MenuOption, MenuOptionHandler> handlers;

  private Locale currentLocale = ENGLISH_LOCALE;

  public MenuController(
          MenuView menuView, MenuPromptView promptView, MenuInput menuInput, ApartmentController mainService,
          ApartmentProvider apartmentProvider, List<View> views
  ) {
    this.menuView = menuView;
    this.menuPromptView = promptView;
    this.menuInput = menuInput;
    this.apartmentProvider = apartmentProvider;
    this.views = views;

    handlers = handlersInit(mainService);
  }

  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    LOGGER.info("program started");

    if (!checkIfApartmentsAreLoaded()) {
      return;
    }

    while (true) {
      try {
        menuView.showMenu();

        menuPromptView.printSelectMenuOptionPrompt();
        String menuOptionName = menuInput.requestMenuOptionName();
        MENU_OPTION_VALIDATOR.validate(menuOptionName);

        MenuOption menuOption = MenuOption.byCommand(menuOptionName);
        LOGGER.info("menu option selected: " + menuOption);
        handlerByOption(menuOption).handle();
      } catch (UnknownOptionException e) {
        LOGGER.warn("unknown menu option", e);
        menuView.showError(e);
      }
    }
  }

  private MenuOptionHandler handlerByOption(MenuOption menuOption) {
    return handlers.get(menuOption);
  }

  private Map<MenuOption, MenuOptionHandler> handlersInit(ApartmentController apartmentController) {
    return Map.of(
            MenuOption.SHOW_ALL, apartmentController::runShowAll,
            MenuOption.SEARCH_BY_ROOMS, apartmentController::runSearchByRooms,
            MenuOption.SEARCH_BY_AREA_AND_FLOOR, apartmentController::runSearchByAreaAndFloor,
            MenuOption.ADD_APARTMENT, apartmentController::addApartment,
            MenuOption.SWITCH_LOCALE, () -> {
              if (currentLocale.equals(ENGLISH_LOCALE)) {
                setLocale(RUSSIAN_LOCALE);
              } else {
                setLocale(ENGLISH_LOCALE);
              }
              menuView.notifyLocaleChanged();
            },
            MenuOption.EXIT, () -> {
              apartmentController.saveApartments();
              System.exit(0);
            }
    );
  }

  private boolean checkIfApartmentsAreLoaded() {
    try {
      apartmentProvider.allApartments();
      return true;
    } catch (FailedToReadApartmentsException e) {
      LOGGER.fatal("failed to read apartments", e);
      menuView.showFailedToReadApartmentsError();
      return false;
    }
  }

  public void setLocale(Locale locale) {
    views.forEach(v -> v.setLocale(locale));
    currentLocale = locale;
  }

  public interface MenuOptionHandler {
    void handle();
  }
}
