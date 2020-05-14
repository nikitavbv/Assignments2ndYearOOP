package com.nikitavbv.univ.oop.lab;

import com.nikitavbv.univ.oop.lab.controllers.ApartmentController;
import com.nikitavbv.univ.oop.lab.controllers.MenuController;
import com.nikitavbv.univ.oop.lab.input.AddApartmentInput;
import com.nikitavbv.univ.oop.lab.input.ApartmentSearchUserInput;
import com.nikitavbv.univ.oop.lab.input.MenuInput;
import com.nikitavbv.univ.oop.lab.models.persistence.FileApartmentReader;
import com.nikitavbv.univ.oop.lab.models.persistence.FileApartmentWriter;
import com.nikitavbv.univ.oop.lab.providers.ApartmentProvider;
import com.nikitavbv.univ.oop.lab.providers.FileApartmentProvider;
import com.nikitavbv.univ.oop.lab.services.ApartmentSearchService;
import com.nikitavbv.univ.oop.lab.services.MessagesService;
import com.nikitavbv.univ.oop.lab.views.AddApartmentView;
import com.nikitavbv.univ.oop.lab.views.ApartmentSearchUserPromptView;
import com.nikitavbv.univ.oop.lab.views.ApartmentsView;
import com.nikitavbv.univ.oop.lab.views.MenuPromptView;
import com.nikitavbv.univ.oop.lab.views.MenuView;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
  private static final File APARTMENTS_DATA_FILE = new File("apartments.json");

  private static final MessagesService MESSAGES_SERVICE = new MessagesService();

  private static final FileApartmentWriter APARTMENT_WRITER = new FileApartmentWriter(APARTMENTS_DATA_FILE);
  private static final ApartmentProvider APARTMENT_PROVIDER =
          new FileApartmentProvider(new FileApartmentReader(APARTMENTS_DATA_FILE));

  private static final MenuView MENU_VIEW = new MenuView(System.out, System.out, MESSAGES_SERVICE);
  private static final MenuPromptView MENU_PROMPT_VIEW = new MenuPromptView(System.out, MESSAGES_SERVICE);
  private static final MenuInput MENU_INPUT = new MenuInput(System.in);

  private static final ApartmentsView APARTMENTS_VIEW = new ApartmentsView(System.out, System.out, MESSAGES_SERVICE);
  private static final ApartmentSearchUserPromptView APARTMENT_SEARCH_USER_PROMPT_VIEW =
          new ApartmentSearchUserPromptView(System.out, MESSAGES_SERVICE);
  private static final ApartmentSearchUserInput USER_INPUT = new ApartmentSearchUserInput(System.in);
  private static final ApartmentSearchService APARTMENT_SEARCH_SERVICE = new ApartmentSearchService(APARTMENT_PROVIDER);
  private static final AddApartmentView ADD_APARTMENT_VIEW = new AddApartmentView(System.out, MESSAGES_SERVICE);
  private static final AddApartmentInput ADD_APARTMENT_INPUT = new AddApartmentInput(System.in);
  private static final ApartmentController MAIN_CONTROLLER = new ApartmentController(
          APARTMENT_SEARCH_SERVICE, APARTMENTS_VIEW, APARTMENT_SEARCH_USER_PROMPT_VIEW, USER_INPUT, APARTMENT_WRITER,
          ADD_APARTMENT_VIEW, ADD_APARTMENT_INPUT, APARTMENT_PROVIDER
  );

  public static void main(String[] args) {
    new MenuController(
            MENU_VIEW,
            MENU_PROMPT_VIEW,
            MENU_INPUT,
            MAIN_CONTROLLER,
            APARTMENT_PROVIDER,
            MESSAGES_SERVICE
    ).run();
  }
}
