package ar.uba.fi.tdd.rulogic.knowledgebase;

import java.io.FileNotFoundException;

public interface DatabaseReader {

  Database readDatabase(String databasePath)
      throws FileNotFoundException, IllegalDatabaseFormatException;

}
