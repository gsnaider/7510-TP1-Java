package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Fact;

public class FactParserImpl implements FactParser {

  private Validator validator;

  public FactParserImpl(Validator validator) {
    this.validator = validator;
  }

  @Override
  public Fact parseFact(String fact) {
    // TODO
    return null;
  }

}
