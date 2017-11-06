package ar.uba.fi.tdd.rulogic.knowledgebase;

import ar.uba.fi.tdd.rulogic.model.Query;

public interface QueryParser {

  Query parseQuery(String query);

}
