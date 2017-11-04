package ar.uba.fi.tdd.rulogic.database;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.database.DatabaseReader;
import ar.uba.fi.tdd.rulogic.database.IllegalDatabaseFormatException;

public class InvalidDatabaseTest {

  private static final String INVALID_RULE_DATABASE_PATH = "invalid-rule.db";
  private static final String INVALID_STATEMENT_DATABASE_PATH = "invalid-statement.db";

  @Test
  public void testNewKnowledgeBase_DatabaseWithInvalidRule_ThrowsIllegalDatabaseFormatException() {
    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      DatabaseReader databaseReader = new DatabaseReader();
      databaseReader.readDatabase(INVALID_RULE_DATABASE_PATH);
    }).withMessageContaining("Error parsing database").withMessageContaining(
        "hijo(X) :- varon(Y).");
  }

  @Test
  public void testNewKnowledgeBase_DatabaseWithInvalidStatement_ThrowsIllegalDatabaseFormatException() {
    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      DatabaseReader databaseReader = new DatabaseReader();
      databaseReader.readDatabase(INVALID_STATEMENT_DATABASE_PATH);
    }).withMessageContaining("Error parsing database").withMessageContaining("varon");
  }
}
