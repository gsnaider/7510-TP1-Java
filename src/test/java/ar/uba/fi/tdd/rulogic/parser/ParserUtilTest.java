package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ParserUtilTest {

  @Test
  public void testParseName_validStatement_ReturnsName() {
    assertThat(ParserUtil.parseName("varon(juan).")).isEqualTo("varon");
    assertThat(ParserUtil.parseName("padre(juan, pepe).")).isEqualTo("padre");
    assertThat(ParserUtil.parseName("hijo(juan, pepe)")).isEqualTo("hijo");
    assertThat(ParserUtil.parseName("hijo(X, Y) :- varon(X), padre(Y, X).")).isEqualTo("hijo");
  }

  @Test
  public void testParseParameters_validStatement_ReturnsParameters() {
    assertThat(ParserUtil.parseParameters("varon(juan).")).containsExactly("juan");
    assertThat(ParserUtil.parseParameters("padre(juan, pepe).")).containsExactly("juan", "pepe");
    assertThat(ParserUtil.parseParameters("hijo(juan, pepe)")).containsExactly("juan", "pepe");
    assertThat(ParserUtil.parseParameters("hijo(X, Y) :- varon(X), padre(Y, X)."))
        .containsExactly("X", "Y");
  }

}
