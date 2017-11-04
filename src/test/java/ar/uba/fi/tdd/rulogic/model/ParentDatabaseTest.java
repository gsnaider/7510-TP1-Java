package ar.uba.fi.tdd.rulogic.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ParentDatabaseTest {

  private KnowledgeBase knowledgeBase;

  @Before
  public void setUp() throws Exception {
    knowledgeBase = new KnowledgeBase();
  }

  @Test
  public void testAnswer_PresentFact_ReturnsTrue() {
    assertThat(knowledgeBase.answer("varon(juan)")).isTrue();
    assertThat(knowledgeBase.answer("mujer(maria)")).isTrue();
    assertThat(knowledgeBase.answer("padre(juan, pepe)")).isTrue();
    assertThat(knowledgeBase.answer("padre(roberto, cecilia)")).isTrue();
    assertThat(knowledgeBase.answer("hermano(nicolas, roberto)")).isTrue();
  }

  @Test
  public void testAnswer_NonPresentFact_ReturnsFalse() {
    assertThat(knowledgeBase.answer("mujer(juan)")).isFalse();
    assertThat(knowledgeBase.answer("varon(maria)")).isFalse();
    assertThat(knowledgeBase.answer("padre(pepe, juan)")).isFalse();
    assertThat(knowledgeBase.answer("padre(hector, cecilia)")).isFalse();
    assertThat(knowledgeBase.answer("hermano(pepe, juan)")).isFalse();
    assertThat(knowledgeBase.answer("hermana(maria, juan)")).isFalse();
  }

  @Test
  public void testAnswer_PresentRule_ReturnsTrue() {
    assertThat(knowledgeBase.answer("hijo(pepe, juan)")).isTrue();
    assertThat(knowledgeBase.answer("hija(maria, hector)")).isTrue();
    assertThat(knowledgeBase.answer("tio(nicolas, alejandro, roberto)")).isTrue();
  }

  @Test
  public void testAnswer_NonPresentRule_ReturnsFalse() {
    assertThat(knowledgeBase.answer("hijo(pepe, hector)")).isFalse();
    assertThat(knowledgeBase.answer("hija(maria, roberto)")).isFalse();
    assertThat(knowledgeBase.answer("tia(julia, alejandro, roberto)")).isFalse();
  }

}
