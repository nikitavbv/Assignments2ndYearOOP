package com.nikitavbv.univ.oop.lab.providers;

import com.google.gson.JsonSyntaxException;
import com.nikitavbv.univ.oop.lab.models.Apartment;
import com.nikitavbv.univ.oop.lab.models.persistence.FileApartmentReader;
import com.nikitavbv.univ.oop.lab.providers.exceptions.FailedToReadApartmentsException;
import java.io.IOException;
import java.util.Arrays;

public class FileApartmentProvider implements ApartmentProvider {

  private final FileApartmentReader reader;
  private Apartment[] apartments;

  public FileApartmentProvider(FileApartmentReader reader) {
    this.reader = reader;
  }

  @Override
  public Apartment[] allApartments() {
    if (this.apartments == null) {
      try {
        this.apartments = reader.readApartments();
      } catch(IOException | JsonSyntaxException e) {
        throw new FailedToReadApartmentsException(e);
      }
    }

    return apartments;
  }

  @Override
  public void addNew(Apartment apartment) {
    Apartment[] newApartments = Arrays.copyOf(apartments, apartments.length + 1);
    newApartments[newApartments.length - 1] = apartment;
    apartments = newApartments;
  }
}
