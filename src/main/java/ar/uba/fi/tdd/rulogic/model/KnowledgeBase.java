package ar.uba.fi.tdd.rulogic.model;

/**
 * Class for running queries on a {@link Statement} database.
 */
public class KnowledgeBase {

  /**
   * Creates a new KnowledgeBase with the database from {@code databasePath}.
   *
   * @throws IllegalDatabaseFormatException if the database within {@code databasePath} has an
   *         illegal format.
   */
  public KnowledgeBase(String databasePath) throws IllegalDatabaseFormatException {
    // TODO Auto-generated constructor stub
  }

  /**
   * Returns {@code true} if the {@link Statement} represented by {@code query} is present within
   * this KnowledgeBase's database.
   *
   * <p>
   * A valid query must start a non-empty string followed by its parameters, which must also be
   * non-empty strings separated by a comma, enclosed within parentheses. A valid query must have at
   * least one parameter. There can be any number of whitespace between the strings and symbols.
   * <p>
   * For example, these are valid queries:
   *
   * <pre>
   * varon(juan)
   * padre(juan, pepe)
   * </pre>
   *
   * And these are not valid queries:
   *
   * <pre>
   * varon
   * varon()
   * padre(juan,)
   * varon(juan).
   * </pre>
   *
   * @throws IllegalArgumentException if the {@code query} has an invalid format
   */
  public boolean answer(String query) {
    return true;
  }

}
