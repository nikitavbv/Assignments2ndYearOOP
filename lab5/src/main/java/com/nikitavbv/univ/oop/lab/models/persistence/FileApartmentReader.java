package com.nikitavbv.univ.oop.lab.models.persistence;

import com.google.gson.Gson;
import com.nikitavbv.univ.oop.lab.models.Apartment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileApartmentReader {

  private final Gson gson = new Gson();
  private final File file;

  public FileApartmentReader(File file) {
    this.file = file;
  }

  public Apartment[] readApartments() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder builder = new StringBuilder();

    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line);
    }

    reader.close();

    return gson.fromJson(builder.toString(), Apartment[].class);
  }
}
