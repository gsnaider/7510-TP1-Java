package ar.uba.fi.tdd.rulogic.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.common.io.Resources;
import ar.uba.fi.tdd.rulogic.model.Statement;
import ar.uba.fi.tdd.rulogic.parser.FactParser;
import ar.uba.fi.tdd.rulogic.parser.RuleParser;

public class DatabaseReader {

  private final FactParser factParser;
  private final RuleParser ruleParser;

  public DatabaseReader(FactParser factParser, RuleParser ruleParser) {
    this.factParser = factParser;
    this.ruleParser = ruleParser;
  }

  public Database readDatabase(String databasePath) throws FileNotFoundException {
    Database.Builder databaseBuilder = Database.builder();

    File file = new File(Resources.getResource(databasePath).getFile());
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Statement statement = parseLine(line);
        databaseBuilder.addStatement(statement);
      }
    }
    return databaseBuilder.build();
  }

  private Statement parseLine(String line) {
    // TODO
    return null;
  }

}
