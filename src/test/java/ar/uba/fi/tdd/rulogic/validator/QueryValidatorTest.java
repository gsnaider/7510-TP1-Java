package ar.uba.fi.tdd.rulogic.validator;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.QueryTestData;
import ar.uba.fi.tdd.rulogic.parser.Validator;

public class QueryValidatorTest {

  private Validator<String> queryValidator;

  @Before
  public void setUp() {
    queryValidator = new QueryValidator();
  }

  @Test
  public void testIsValid_ValidQuery_ReturnsTrue() {
    assertThat(queryValidator.isValid(QueryTestData.QUERY_1_STRING)).isTrue();
    assertThat(queryValidator.isValid(QueryTestData.QUERY_2_STRING)).isTrue();
  }

  @Test
  public void testIsValid_InvalidQuery_ReturnsFalse() {
    assertThat(queryValidator.isValid("varon")).isFalse();
    assertThat(queryValidator.isValid("varon()")).isFalse();
    assertThat(queryValidator.isValid("varon(juan).")).isFalse();
    assertThat(queryValidator.isValid("varon(juan,)")).isFalse();
    assertThat(queryValidator.isValid("varon(juan")).isFalse();
    assertThat(queryValidator.isValid("(juan)")).isFalse();
  }

}
