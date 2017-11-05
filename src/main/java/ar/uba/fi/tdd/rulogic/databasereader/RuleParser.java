package ar.uba.fi.tdd.rulogic.databasereader;

import ar.uba.fi.tdd.rulogic.model.Rule;

public interface RuleParser {

  Rule parseRule(String rule);

}
