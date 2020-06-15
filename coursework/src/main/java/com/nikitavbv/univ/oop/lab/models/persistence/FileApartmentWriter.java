package com.nikitavbv.univ.oop.lab.models.persistence;

import com.google.gson.Gson;
import com.nikitavbv.univ.oop.lab.models.Apartment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileApartmentWriter {

  private final Gson gson = new Gson();
  private final File file;

  public FileApartmentWriter(File file) {
    this.file = file;
  }

  public void saveApartments(Apartment[] apartments) throws IOException {
    FileWriter writer = new FileWriter(file);
    writer.write(gson.toJson(apartments));
    writer.close();
  }
}
