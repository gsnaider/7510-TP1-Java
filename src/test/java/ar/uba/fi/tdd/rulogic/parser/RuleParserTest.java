package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;

public class RuleParserTest {

  private RuleParser ruleParser;

  @Mock
  private RuleValidator ruleValidator;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    ruleParser = new RuleParserImpl(ruleValidator);
    when(ruleValidator.isValid(anyString())).thenReturn(true);
    when(ruleValidator.hasValidParameters(any(Rule.class))).thenReturn(true);
  }

  @Test
  public void testParseRule_ValidRule_ReturnsRule() {
    Rule rule = ruleParser.parseRule(RuleTestData.RULE_STRING);
    assertThat(rule).isEqualTo(RuleTestData.RULE);
  }

  @Test
  public void testParseFact_InvalidRule_ThrowsIllegalArgumentException() {
    when(ruleValidator.isValid(anyString())).thenReturn(false);
    assertThatIllegalArgumentException().isThrownBy(() -> {
      ruleParser.parseRule(RuleTestData.INVALID_RULE_STRING);
    }).withMessageContaining(RuleTestData.INVALID_RULE_STRING);
  }

  @Test
  public void testParseFact_InvalidRuleParameters_ThrowsIllegalArgumentException() {
    when(ruleValidator.hasValidParameters(any(Rule.class))).thenReturn(false);
    assertThatIllegalArgumentException().isThrownBy(() -> {
      ruleParser.parseRule(RuleTestData.INVALID_RULE_STRING);
    }).withMessageContaining(RuleTestData.INVALID_RULE_STRING);
  }
}
