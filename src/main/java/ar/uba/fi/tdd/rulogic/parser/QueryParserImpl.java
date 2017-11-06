package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.knowledgebase.QueryParser;
import ar.uba.fi.tdd.rulogic.model.Query;

public class QueryParserImpl implements QueryParser {

  private final Validator<String> queryValidator;

  public QueryParserImpl(Validator<String> queryValidator) {
    this.queryValidator = queryValidator;
  }

  @Override
  public Query parseQuery(String query) {
    // TODO
    return null;
  }

}
