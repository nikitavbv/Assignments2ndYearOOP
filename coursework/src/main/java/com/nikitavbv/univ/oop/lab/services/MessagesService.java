package com.nikitavbv.univ.oop.lab.services;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesService {

  private static final String BUNDLE_NAME = "MessagesResources";

  private static final Locale DEFAULT_LOCALE = Locale.getDefault();
  private static final Locale ENGLISH_LOCALE = new Locale("en", "US");
  private static final Locale RUSSIAN_LOCALE = new Locale("ru", "RU");

  private Locale locale = DEFAULT_LOCALE;
  private ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, DEFAULT_LOCALE);

  public String getString(String key) {
    return resourceBundle.getString(key);
  }

  public void switchToEnglish() {
    locale = ENGLISH_LOCALE;
    reloadResourceBundle();
  }

  public void switchToRussian() {
    locale = RUSSIAN_LOCALE;
    reloadResourceBundle();
  }

  private void reloadResourceBundle() {
    resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
  }

  public boolean isEnglish() {
    return locale.equals(ENGLISH_LOCALE);
  }
}
