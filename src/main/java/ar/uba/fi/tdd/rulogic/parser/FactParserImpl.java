package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Fact;

public class FactParserImpl implements FactParser {

  private final Validator<String> factValidator;

  public FactParserImpl(Validator<String> factValidator) {
    this.factValidator = factValidator;
  }

  @Override
  public Fact parseFact(String fact) {
    if (!factValidator.isValid(fact)) {
      throw new IllegalArgumentException(String.format("Invalid fact: %s", fact));
    }
    return Fact
        .builder()
        .name(ParserUtil.parseName(fact))
        .parameters(ParserUtil.parseParameters(fact))
        .build();
  }

}
