package ar.uba.fi.tdd.rulogic.databasereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.common.io.Resources;
import ar.uba.fi.tdd.rulogic.knowledgebase.Database;
import ar.uba.fi.tdd.rulogic.knowledgebase.DatabaseReader;
import ar.uba.fi.tdd.rulogic.model.DatabaseImpl;
import ar.uba.fi.tdd.rulogic.model.Statement;

public class DatabaseReaderImpl implements DatabaseReader {

  private final StatementParser statementParser;

  public DatabaseReaderImpl(StatementParser statementParser) {
    this.statementParser = statementParser;
  }

  @Override
  public Database readDatabase(String databasePath) throws FileNotFoundException {
    DatabaseImpl.Builder databaseBuilder = DatabaseImpl.builder();

    File file = new File(Resources.getResource(databasePath).getFile());
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Statement statement = statementParser.parseStatement(line);
        statement.addToDatabase(databaseBuilder);
      }
    }
    return databaseBuilder.build();
  }

}
