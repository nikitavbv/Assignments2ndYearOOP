package com.nikitavbv.univ.oop.lab;

import com.nikitavbv.univ.oop.lab.controllers.ApartmentController;
import com.nikitavbv.univ.oop.lab.controllers.MenuController;
import com.nikitavbv.univ.oop.lab.input.ApartmentSearchUserInput;
import com.nikitavbv.univ.oop.lab.input.MenuInput;
import com.nikitavbv.univ.oop.lab.providers.ApartmentProvider;
import com.nikitavbv.univ.oop.lab.providers.RandomApartmentProvider;
import com.nikitavbv.univ.oop.lab.services.ApartmentSearchService;
import com.nikitavbv.univ.oop.lab.views.ApartmentSearchUserPromptView;
import com.nikitavbv.univ.oop.lab.views.ApartmentsView;
import com.nikitavbv.univ.oop.lab.views.MenuPromptView;
import com.nikitavbv.univ.oop.lab.views.MenuView;
import java.util.Random;

public class Main {

  private static final ApartmentProvider APARTMENT_PROVIDER = new RandomApartmentProvider(
          new Random(),
          40
  );

  private static final MenuView MENU_VIEW = new MenuView(System.out, System.err);
  private static final MenuPromptView MENU_PROMPT_VIEW = new MenuPromptView(System.out);
  private static final MenuInput MENU_INPUT = new MenuInput(System.in);

  private static final ApartmentsView APARTMENTS_VIEW = new ApartmentsView(System.out, System.err);
  private static final ApartmentSearchUserPromptView APARTMENT_SEARCH_USER_PROMPT_VIEW =
          new ApartmentSearchUserPromptView(System.out);
  private static final ApartmentSearchUserInput USER_INPUT = new ApartmentSearchUserInput(System.in);
  private static final ApartmentSearchService APARTMENT_SEARCH_SERVICE = new ApartmentSearchService();
  private static final ApartmentController MAIN_CONTROLLER = new ApartmentController(
          APARTMENT_PROVIDER, APARTMENT_SEARCH_SERVICE, APARTMENTS_VIEW, APARTMENT_SEARCH_USER_PROMPT_VIEW, USER_INPUT
  );

  public static void main(String[] args) {
    new MenuController(
            MENU_VIEW,
            MENU_PROMPT_VIEW,
            MENU_INPUT,
            MAIN_CONTROLLER
    ).run();
  }
}
