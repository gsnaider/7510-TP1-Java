package ar.uba.fi.tdd.rulogic.model;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import org.junit.Before;
import org.junit.Test;

public class IncompleteDatabaseTest {

  private KnowledgeBase knowledgeBase;

  @Before
  public void setUp() throws Exception {
    knowledgeBase = new KnowledgeBase();
  }

  @Test
  public void testAnswer_WithIncompleteDatabase_ThrowsException() {
    assertThatIllegalStateException().isThrownBy(() -> {
      knowledgeBase.answer("varon(juan)");
    }).withMessageContaining("Database is not initialized");
  }

}
