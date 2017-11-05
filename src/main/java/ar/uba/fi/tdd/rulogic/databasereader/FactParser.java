package ar.uba.fi.tdd.rulogic.databasereader;

import ar.uba.fi.tdd.rulogic.model.Fact;

public interface FactParser {

  Fact parseFact(String fact);

}
