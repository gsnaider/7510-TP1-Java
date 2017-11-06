package ar.uba.fi.tdd.rulogic.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.knowledgebase.Database;

public class DatabaseTest {

  @Test
  public void testContains_EmptyDatabase_ReturnsFalse() {
    Database database = DatabaseTestData.EMPTY_DATABASE;
    assertThat(database.contains(QueryTestData.QUERY_1)).isFalse();
    assertThat(database.contains(QueryTestData.QUERY_2)).isFalse();
  }

  @Test
  public void testContains_PresentStatement_ReturnsTrue() {
    Database database = DatabaseTestData.SMALL_DATABASE;
    assertThat(database.contains(QueryTestData.QUERY_1)).isTrue();
    assertThat(database.contains(QueryTestData.QUERY_2)).isTrue();
    assertThat(database.contains(QueryTestData.QUERY_3)).isTrue();
  }

  @Test
  public void testContains_NonPresentStatement_ReturnsFalse() {
    Database database = DatabaseTestData.SMALL_DATABASE;
    assertThat(database.contains(QueryTestData.NON_PRESENT_QUERY_1)).isFalse();
    assertThat(database.contains(QueryTestData.NON_PRESENT_QUERY_2)).isFalse();
    assertThat(database.contains(QueryTestData.NON_PRESENT_QUERY_3)).isFalse();
  }

}
