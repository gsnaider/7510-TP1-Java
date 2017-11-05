package ar.uba.fi.tdd.rulogic.database;

import ar.uba.fi.tdd.rulogic.parser.FactParser;
import ar.uba.fi.tdd.rulogic.parser.RuleParser;

public class DatabaseReader {

  private final FactParser factParser;
  private final RuleParser ruleParser;

  public DatabaseReader(FactParser factParser, RuleParser ruleParser) {
    this.factParser = factParser;
    this.ruleParser = ruleParser;
  }

  public Database readDatabase(String databasePath) {
    // TODO
    return null;
  }

}
