package ar.uba.fi.tdd.rulogic.knowledgebase;

import ar.uba.fi.tdd.rulogic.model.Query;

public interface Database {

  boolean contains(Query query);

}
