package ar.uba.fi.tdd.rulogic.knowledgebase;

import ar.uba.fi.tdd.rulogic.model.Statement;

public interface Database {

  boolean contains(Statement statement);

}
