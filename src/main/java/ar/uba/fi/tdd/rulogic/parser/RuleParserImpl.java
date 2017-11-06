package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Rule;

public class RuleParserImpl implements RuleParser {

  private RuleValidator ruleValidator;

  public RuleParserImpl(RuleValidator ruleValidator) {
    this.ruleValidator = ruleValidator;
  }

  @Override
  public Rule parseRule(String rule) {
    // TODO
    return null;
  }

}
