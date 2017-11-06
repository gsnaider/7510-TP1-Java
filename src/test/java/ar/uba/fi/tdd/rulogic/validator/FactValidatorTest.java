package ar.uba.fi.tdd.rulogic.validator;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.parser.Validator;

public class FactValidatorTest {

  private Validator<String> factValidator;

  @Before
  public void setUp() {
    factValidator = new FactValidator();
  }

  @Test
  public void testIsValid_ValidFact_ReturnsTrue() {
    assertThat(factValidator.isValid(FactTestData.FACT_1_STRING)).isTrue();
    assertThat(factValidator.isValid(FactTestData.FACT_2_STRING)).isTrue();
  }

  @Test
  public void testIsValid_InvalidFact_ReturnsFalse() {
    assertThat(factValidator.isValid("varon")).isFalse();
    assertThat(factValidator.isValid("varon.")).isFalse();
    assertThat(factValidator.isValid("varon().")).isFalse();
    assertThat(factValidator.isValid("varon(juan)")).isFalse();
    assertThat(factValidator.isValid("varon(juan,).")).isFalse();
    assertThat(factValidator.isValid("varon(juan.")).isFalse();
    assertThat(factValidator.isValid("(juan).")).isFalse();
  }

}
