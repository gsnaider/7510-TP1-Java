package ar.uba.fi.tdd.rulogic.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.uba.fi.tdd.rulogic.databasereader.DatabaseReaderImpl;
import ar.uba.fi.tdd.rulogic.databasereader.StatementParser;
import ar.uba.fi.tdd.rulogic.knowledgebase.Database;
import ar.uba.fi.tdd.rulogic.knowledgebase.DatabaseReader;
import ar.uba.fi.tdd.rulogic.knowledgebase.IllegalDatabaseFormatException;
import ar.uba.fi.tdd.rulogic.model.DatabaseTestData;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;

public class DatabaseReaderTest {

  private DatabaseReader databaseReader;

  @Mock
  private StatementParser statementParser;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    databaseReader = new DatabaseReaderImpl(statementParser);
    when(statementParser.parseStatement(FactTestData.FACT_1_STRING))
        .thenReturn(FactTestData.FACT_1);
    when(statementParser.parseStatement(FactTestData.FACT_2_STRING))
        .thenReturn(FactTestData.FACT_2);
    when(statementParser.parseStatement(RuleTestData.RULE_STRING)).thenReturn(RuleTestData.RULE);
  }

  @Test
  public void testReadDatabase_EmptyDatabase_ReturnsEmptyDatabase() throws FileNotFoundException {
    Database database = databaseReader.readDatabase(DatabaseTestData.EMPTY_DATABASE_PATH);
    assertThat(database).isEqualTo(DatabaseTestData.EMPTY_DATABASE);
  }

  @Test
  public void testReadDatabase_ValidDatabase_ReturnsDatabase() throws FileNotFoundException {
    Database database = databaseReader.readDatabase(DatabaseTestData.SMALL_DATABASE_PATH);
    assertThat(database).isEqualTo(DatabaseTestData.SMALL_DATABASE);
  }

  @Test
  public void testReadDatabase_DatabaseWithInvalidRule_ThrowsIllegalDatabaseFormatException() {
    String errorMessage = "Error parsing database. Invalid rule hijo(X) :- varon(Y)";
    when(statementParser.parseStatement(RuleTestData.RULE_STRING))
        .thenThrow(new IllegalArgumentException(errorMessage));

    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      databaseReader.readDatabase(DatabaseTestData.SMALL_DATABASE_PATH);
    }).withMessage(errorMessage);
  }

  @Test
  public void testReadDatabase_DatabaseWithInvalidFact_ThrowsIllegalDatabaseFormatException() {
    String errorMessage = "Error parsing database. Invalid fact varon";
    when(statementParser.parseStatement(FactTestData.FACT_1_STRING))
        .thenThrow(new IllegalArgumentException(errorMessage));

    assertThatExceptionOfType(IllegalDatabaseFormatException.class).isThrownBy(() -> {
      databaseReader.readDatabase(DatabaseTestData.SMALL_DATABASE_PATH);
    }).withMessage(errorMessage);
  }

}
