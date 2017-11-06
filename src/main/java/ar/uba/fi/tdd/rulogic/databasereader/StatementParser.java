package ar.uba.fi.tdd.rulogic.databasereader;

import ar.uba.fi.tdd.rulogic.model.Statement;

public interface StatementParser {

  /**
   * Attempts to parse a statement String to a {@link Statement}.
   * 
   * @param statementLine the String to be parsed.
   * @throws IllegalArgumentException if the {@code statementLine} has an invalid format, and thus
   *         cannot be parsed.
   */
  public Statement parseStatement(String statementLine);

}
