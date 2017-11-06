package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.FactTestData;
import ar.uba.fi.tdd.rulogic.validator.FactValidatorImpl;

public class FactParserIntegrationTest {

  private FactParser factParser;

  @Before
  public void setUp() {
    Validator factValidator = new FactValidatorImpl();
    factParser = new FactParserImpl(factValidator);
  }

  @Test
  public void testParseFact_ValidFact_ReturnsFact() {
    Fact fact = factParser.parseFact(FactTestData.FACT_1_STRING);
    assertThat(fact).isEqualTo(FactTestData.FACT_1);

    fact = factParser.parseFact(FactTestData.FACT_2_STRING);
    assertThat(fact).isEqualTo(FactTestData.FACT_2);
  }

  @Test
  public void testParseFact_InvalidFact_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      factParser.parseFact(FactTestData.INVALID_FACT_STRING);
    }).withMessageContaining(FactTestData.INVALID_FACT_STRING);
  }
}
