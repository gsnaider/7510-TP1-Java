package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.databasereader.StatementParser;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;
import ar.uba.fi.tdd.rulogic.model.Statement;
import ar.uba.fi.tdd.rulogic.validator.FactValidator;

public class StatementParserIntegrationTest {

  private StatementParser statementParser;

  @Before
  public void setUp() {
    Validator<String> factValidator = new FactValidator();
    FactParser factParser = new FactParserImpl(factValidator);
    RuleParser ruleParser = new RuleParserImpl();
    statementParser = new StatementParserImpl(factParser, ruleParser);
  }

  @Test
  public void testParseStatement_ValidFact_ReturnsFact() {
    Statement statement = statementParser.parseStatement(FactTestData.FACT_1_STRING);
    assertThat(statement).isInstanceOf(Fact.class);
    Fact fact = (Fact) statement;
    assertThat(fact).isEqualTo(FactTestData.FACT_1);

    statement = statementParser.parseStatement(FactTestData.FACT_2_STRING);
    assertThat(statement).isInstanceOf(Fact.class);
    fact = (Fact) statement;
    assertThat(fact).isEqualTo(FactTestData.FACT_2);
  }

  @Test
  public void testParseStatement_ValidRule_ReturnsRule() {
    Statement statement = statementParser.parseStatement(RuleTestData.RULE_STRING);
    assertThat(statement).isInstanceOf(Rule.class);
    Rule rule = (Rule) statement;
    assertThat(rule).isEqualTo(RuleTestData.RULE);
  }

  @Test
  public void testParseStatement_InvalidFact_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      statementParser.parseStatement(FactTestData.INVALID_FACT_STRING);
    }).withMessageContaining(FactTestData.INVALID_FACT_STRING);
  }

  @Test
  public void testParseStatement_InvalidRule_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      statementParser.parseStatement(RuleTestData.INVALID_RULE_STRING);
    }).withMessageContaining(RuleTestData.INVALID_RULE_STRING);
  }

}
