package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.FactTestData;

public class FactParserTest {

  private FactParser factParser;

  @Mock
  private Validator<String> factValidator;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    factParser = new FactParserImpl(factValidator);
  }

  @Test
  public void testParseFact_ValidFact_ReturnsFact() {
    when(factValidator.isValid(anyString())).thenReturn(true);

    Fact fact = factParser.parseFact(FactTestData.FACT_1_STRING);
    assertThat(fact).isEqualTo(FactTestData.FACT_1);

    fact = factParser.parseFact(FactTestData.FACT_2_STRING);
    assertThat(fact).isEqualTo(FactTestData.FACT_2);

    fact = factParser.parseFact(FactTestData.FACT_2_STRING_WITH_WHITESPACE);
    assertThat(fact).isEqualTo(FactTestData.FACT_2);
  }

  @Test
  public void testParseFact_InvalidFact_ThrowsIllegalArgumentException() {
    when(factValidator.isValid(anyString())).thenReturn(false);
    assertThatIllegalArgumentException().isThrownBy(() -> {
      factParser.parseFact(FactTestData.INVALID_FACT_STRING);
    }).withMessageContaining(FactTestData.INVALID_FACT_STRING);
  }
}
