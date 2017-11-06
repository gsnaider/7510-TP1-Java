package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.FactTestData;

public class FactParserTest {

  private FactParser factParser;

  @Before
  public void setUp() {
    factParser = new FactParserImpl();
  }

  @Test
  public void testParseFact_ValidFact_ReturnsFact() {
    Fact fact = factParser.parseFact(FactTestData.FACT_1_STRING);
    assertThat(fact).isEqualTo(FactTestData.FACT_1);

    fact = factParser.parseFact(FactTestData.FACT_2_STRING);
    assertThat(fact).isEqualTo(FactTestData.FACT_2);

    fact = factParser.parseFact(FactTestData.FACT_2_STRING_WITH_WHITESPACE);
    assertThat(fact).isEqualTo(FactTestData.FACT_2);
  }

  @Test
  public void testParseFact_InvalidFact_ThrowsIllegalArgumentException() {
    for (String invalidFact : FactTestData.INVALID_FACTS) {
      assertThatIllegalArgumentException().isThrownBy(() -> {
        factParser.parseFact(invalidFact);
      }).withMessageContaining(invalidFact);
    }
  }
}