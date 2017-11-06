package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Rule;

public interface RuleParametersValidator {

  public boolean hasValidParameters(Rule rule);

}
