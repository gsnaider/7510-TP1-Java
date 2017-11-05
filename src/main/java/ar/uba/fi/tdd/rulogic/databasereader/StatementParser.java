package ar.uba.fi.tdd.rulogic.databasereader;

import ar.uba.fi.tdd.rulogic.model.Statement;

public interface StatementParser {

  public Statement parseStatement(String statementLine);

}
