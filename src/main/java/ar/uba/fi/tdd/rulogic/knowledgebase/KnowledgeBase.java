package ar.uba.fi.tdd.rulogic.knowledgebase;

import ar.uba.fi.tdd.rulogic.model.Statement;

/**
 * Class for running queries on a {@link Database}.
 */
public class KnowledgeBase {

  private final Database database;
  private final QueryParser queryParser;

  public KnowledgeBase(Database database, QueryParser queryParser)
      throws IllegalDatabaseFormatException {
    this.database = database;
    this.queryParser = queryParser;
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
    return database.contains(queryParser.parseQuery(query));
  }

}
