package ar.uba.fi.tdd.rulogic.knowledgebase;

/**
 * Thrown to indicate that a database has an illegal format.
 */
public class IllegalDatabaseFormatException extends Exception {

  public IllegalDatabaseFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  private static final long serialVersionUID = 1L;

}
