package ar.uba.fi.tdd.rulogic.model;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import org.junit.Before;
import org.junit.Test;

public class InvalidDatabaseTest {

  private KnowledgeBase knowledgeBase;

  @Before
  public void setUp() throws Exception {
    knowledgeBase = new KnowledgeBase();
  }

  @Test
  public void testAnswer_WithInvalidDatabase_ThrowsException() {
    assertThatIllegalStateException().isThrownBy(() -> {
      knowledgeBase.answer("varon(juan)");
    }).withMessageContaining("Database is not initialized");
  }
}
