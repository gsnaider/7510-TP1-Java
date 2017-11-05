package ar.uba.fi.tdd.rulogic.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.databasereader.DatabaseReaderImpl;
import ar.uba.fi.tdd.rulogic.knowledgebase.Database;
import ar.uba.fi.tdd.rulogic.knowledgebase.DatabaseReader;
import ar.uba.fi.tdd.rulogic.knowledgebase.IllegalDatabaseFormatException;
import ar.uba.fi.tdd.rulogic.model.DatabaseTestData;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;
import ar.uba.fi.tdd.rulogic.parser.FactParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleParserImpl;

public class DatabaseReaderIntegrationTest {

  private DatabaseReader databaseReader;

  @Before
  public void setUp() {
    databaseReader = new DatabaseReaderImpl(new FactParserImpl(), new RuleParserImpl());
  }

  @Test
  public void testReadDatabase_EmptyDatabase_ReturnsEmptyDatabase() throws FileNotFoundException {
    Database emptyDatabase = databaseReader.readDatabase(DatabaseTestData.EMPTY_DATABASE_PATH);
    assertThat(emptyDatabase).isEqualTo(DatabaseTestData.EMPTY_DATABASE);
  }

  @Test
  public void testReadDatabase_ValidDatabase_ReturnsParsedDatabase() throws FileNotFoundException {
    Database database = databaseReader.readDatabase(DatabaseTestData.SMALL_DATABASE_PATH);
    assertThat(database).isEqualTo(DatabaseTestData.SMALL_DATABASE);
  }

  @Test
  public void testReadDatabase_DatabaseWithInvalidRule_ThrowsIllegalDatabaseFormatException() {
    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      databaseReader.readDatabase(DatabaseTestData.INVALID_RULE_DATABASE_PATH);
    }).withMessageContaining("Error parsing database").withMessageContaining(
        RuleTestData.INVALID_RULE_STRING);
  }

  @Test
  public void testReadDatabase_DatabaseWithInvalidFact_ThrowsIllegalDatabaseFormatException() {
    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      databaseReader.readDatabase(DatabaseTestData.INVALID_FACT_DATABASE_PATH);
    }).withMessageContaining("Error parsing database").withMessageContaining(
        FactTestData.INVALID_FACT_STRING);
  }
}
