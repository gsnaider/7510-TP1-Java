package ar.uba.fi.tdd.rulogic.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class NumberDatabaseTest {

  private KnowledgeBase knowledgeBase;

  @Before
  public void setUp() throws Exception {
    knowledgeBase = new KnowledgeBase();
  }

  @Test
  public void testAnswer_PresentFact_ReturnsTrue() {
    assertThat(knowledgeBase.answer("add(one, one, two)")).isTrue();
  }

  @Test
  public void testAnswer_NonPresentFact_ReturnsFalse() {
    assertThat(knowledgeBase.answer("add(two, one, one)")).isFalse();
  }

  @Test
  public void testAnswer_PresentRule_ReturnsTrue() {
    assertThat(knowledgeBase.answer("subtract(two, one, one)")).isTrue();
  }

  @Test
  public void testAnswer_NonPresentRule_ReturnsFalse() {
    assertThat(knowledgeBase.answer("subtract(one, one, two)")).isFalse();
  }

}
