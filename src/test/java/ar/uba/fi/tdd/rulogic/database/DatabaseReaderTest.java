package ar.uba.fi.tdd.rulogic.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.TestData;

public class DatabaseReaderTest {

  private static final String EMPTY_DATABASE_PATH = "empty.db";
  private static final String SMALL_DATABASE_PATH = "small.db";
  private static final String INVALID_RULE_DATABASE_PATH = "invalid-rule.db";
  private static final String INVALID_STATEMENT_DATABASE_PATH = "invalid-statement.db";

  private DatabaseReader databaseReader;

  @Before
  public void setUp() {
    databaseReader = new DatabaseReader();
  }

  @Test
  public void testReadDatabase_EmptyDatabase_ReturnsEmptyDatabase() {
    Database emptyDatabase = databaseReader.readDatabase(EMPTY_DATABASE_PATH);
    assertThat(emptyDatabase).isEqualTo(TestData.EMPTY_DATABASE);
  }

  @Test
  public void testReadDatabase_ValidDatabase_ReturnsParsedDatabase() {
    Database database = databaseReader.readDatabase(SMALL_DATABASE_PATH);
    assertThat(database).isEqualTo(TestData.SMALL_DATABASE);
  }

  @Test
  public void testReadDatabase_DatabaseWithInvalidRule_ThrowsIllegalDatabaseFormatException() {
    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      DatabaseReader databaseReader = new DatabaseReader();
      databaseReader.readDatabase(INVALID_RULE_DATABASE_PATH);
    }).withMessageContaining("Error parsing database").withMessageContaining(
        "hijo(X) :- varon(Y).");
  }

  @Test
  public void testReadDatabase_DatabaseWithInvalidStatement_ThrowsIllegalDatabaseFormatException() {
    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      DatabaseReader databaseReader = new DatabaseReader();
      databaseReader.readDatabase(INVALID_STATEMENT_DATABASE_PATH);
    }).withMessageContaining("Error parsing database").withMessageContaining("varon");
  }
}
