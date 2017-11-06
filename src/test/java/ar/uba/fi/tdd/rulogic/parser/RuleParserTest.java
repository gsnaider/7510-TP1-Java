package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;

public class RuleParserTest {

  private RuleParser ruleParser;

  @Before
  public void setUp() {
    ruleParser = new RuleParserImpl();
  }

  @Test
  public void testParseRule_ValidRule_ReturnsRule() {
    Rule rule = ruleParser.parseRule(RuleTestData.RULE_STRING);
    assertThat(rule).isEqualTo(RuleTestData.RULE);
  }

  @Test
  public void testParseFact_InvalidFact_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      ruleParser.parseRule(RuleTestData.INVALID_RULE_STRING);
    }).withMessageContaining(RuleTestData.INVALID_RULE_STRING);
  }
}
