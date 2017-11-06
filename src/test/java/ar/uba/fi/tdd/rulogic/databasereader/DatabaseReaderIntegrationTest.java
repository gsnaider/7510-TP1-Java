package ar.uba.fi.tdd.rulogic.databasereader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.knowledgebase.Database;
import ar.uba.fi.tdd.rulogic.knowledgebase.DatabaseReader;
import ar.uba.fi.tdd.rulogic.knowledgebase.IllegalDatabaseFormatException;
import ar.uba.fi.tdd.rulogic.model.DatabaseTestData;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;
import ar.uba.fi.tdd.rulogic.parser.FactParser;
import ar.uba.fi.tdd.rulogic.parser.FactParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleParser;
import ar.uba.fi.tdd.rulogic.parser.RuleParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleValidator;
import ar.uba.fi.tdd.rulogic.parser.StatementParserImpl;
import ar.uba.fi.tdd.rulogic.parser.Validator;
import ar.uba.fi.tdd.rulogic.validator.FactValidator;
import ar.uba.fi.tdd.rulogic.validator.RuleValidatorImpl;

public class DatabaseReaderIntegrationTest {

  private DatabaseReader databaseReader;

  @Before
  public void setUp() {
    Validator<String> factValidator = new FactValidator();
    FactParser factParser = new FactParserImpl(factValidator);
    RuleValidator ruleValidator = new RuleValidatorImpl();
    RuleParser ruleParser = new RuleParserImpl(ruleValidator);
    StatementParser statementParser = new StatementParserImpl(factParser, ruleParser);
    databaseReader = new DatabaseReaderImpl(statementParser);
  }

  @Test
  public void testReadDatabase_EmptyDatabase_ReturnsEmptyDatabase() throws Exception {
    Database emptyDatabase = databaseReader.readDatabase(DatabaseTestData.EMPTY_DATABASE_PATH);
    assertThat(emptyDatabase).isEqualTo(DatabaseTestData.EMPTY_DATABASE);
  }

  @Test
  public void testReadDatabase_ValidDatabase_ReturnsParsedDatabase() throws Exception {
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
