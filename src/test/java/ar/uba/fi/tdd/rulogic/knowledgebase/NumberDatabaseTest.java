package ar.uba.fi.tdd.rulogic.knowledgebase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.database.DatabaseReader;
import ar.uba.fi.tdd.rulogic.knowledgebase.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.parser.QueryParser;

public class NumberDatabaseTest {

  private static final String NUMBER_DATABASE_PATH = "number.db";

  private KnowledgeBase knowledgeBase;

  @Before
  public void setUp() throws Exception {
    DatabaseReader databaseReader = new DatabaseReader();
    knowledgeBase =
        new KnowledgeBase(databaseReader.readDatabase(NUMBER_DATABASE_PATH), new QueryParser());
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

  @Test
  public void testAnswer_InvalidQuery_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("add");
    }).withMessageContaining("Invalid query");

    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("add()");
    }).withMessageContaining("Invalid query");

    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("subtract(one,two,three).");
    }).withMessageContaining("Invalid query");

    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("add(one,two,)");
    }).withMessageContaining("Invalid query");
  }

}
