package ar.uba.fi.tdd.rulogic.validator;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.RuleTestData;
import ar.uba.fi.tdd.rulogic.parser.RuleValidator;

public class RuleValidatorTest {

  private RuleValidator ruleValidator;

  @Before
  public void setUp() {
    ruleValidator = new RuleValidatorImpl();
  }

  @Test
  public void testIsValid_ValidRule_ReturnsTrue() {
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon(X), padre(Y, X).")).isTrue();
    assertThat(ruleValidator.isValid("hija(X, Y) :- mujer(X), padre(Y, X).")).isTrue();
    assertThat(ruleValidator.isValid("subtract(X, Y, Z) :- add(Y, Z, X).")).isTrue();
    assertThat(ruleValidator.isValid("wet_floor(X) :- rain(X).")).isTrue();
  }

  @Test
  public void testIsValid_InvalidRule_ReturnsFalse() {
    assertThat(ruleValidator.isValid("hijo(X, Y).")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- .")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon.")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon().")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon(X), .")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon(X), padre.")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon(X), padre().")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon(X), padre(X, ).")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X, Y) :- varon(X), padre(X, Y)")).isFalse();
    assertThat(ruleValidator.isValid("hijo :- varon(X), padre(X, Y).")).isFalse();
    assertThat(ruleValidator.isValid("hijo() :- varon(X), padre(X, Y).")).isFalse();
    assertThat(ruleValidator.isValid("hijo(X) varon(X), padre(X, Y).")).isFalse();
  }

  @Test
  public void testHasValidParameters_ValidRule_ReturnsTrue() {
    assertThat(ruleValidator.hasValidParameters(RuleTestData.RULE)).isTrue();
  }

  @Test
  public void testHasValidParameters_InvalidRule_ReturnsFalse() {
    assertThat(ruleValidator.hasValidParameters(RuleTestData.INVALID_RULE)).isFalse();
  }

}
