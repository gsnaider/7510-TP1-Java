package ar.uba.fi.tdd.rulogic.model;

import org.junit.Test;
import nl.jqno.equalsverifier.EqualsVerifier;

public class StatementTest {

  @Test
  public void testEqualsAndHashCode() {
    EqualsVerifier.forClass(Statement.class).verify();
  }
}
