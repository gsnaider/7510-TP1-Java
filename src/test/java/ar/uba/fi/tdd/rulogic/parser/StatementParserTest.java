package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.uba.fi.tdd.rulogic.databasereader.StatementParser;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;
import ar.uba.fi.tdd.rulogic.model.Statement;

public class StatementParserTest {

  private StatementParser statementParser;

  @Mock
  private FactParser factParser;

  @Mock
  private RuleParser ruleParser;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    statementParser = new StatementParserImpl(factParser, ruleParser);
    when(factParser.parseFact(FactTestData.FACT_1_STRING)).thenReturn(FactTestData.FACT_1);
    when(factParser.parseFact(FactTestData.FACT_2_STRING)).thenReturn(FactTestData.FACT_2);
    when(ruleParser.parseRule(RuleTestData.RULE_STRING)).thenReturn(RuleTestData.RULE);
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
    Exception expectedException = new IllegalArgumentException("Invalid fact: varon");
    when(factParser.parseFact(anyString())).thenThrow(expectedException);

    Throwable thrown = catchThrowable(() -> {
      statementParser.parseStatement(FactTestData.INVALID_FACT_STRING);
    });

    assertThat(thrown).isEqualTo(expectedException);

  }

  @Test
  public void testParseStatement_InvalidRule_ThrowsIllegalArgumentException() {
    Exception expectedException = new IllegalArgumentException("Invalid rule: hijo(X) :- varon(Y)");
    when(factParser.parseFact(anyString())).thenThrow(expectedException);

    Throwable thrown = catchThrowable(() -> {
      statementParser.parseStatement(RuleTestData.INVALID_RULE_STRING);
    });

    assertThat(thrown).isEqualTo(expectedException);
  }

}
