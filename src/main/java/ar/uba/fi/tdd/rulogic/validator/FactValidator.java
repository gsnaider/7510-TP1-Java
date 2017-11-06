package ar.uba.fi.tdd.rulogic.validator;

import java.util.regex.Pattern;
import ar.uba.fi.tdd.rulogic.parser.Validator;

public class FactValidator implements Validator<String> {

  private static final String FACT_REGEX = "^\\w+\\((\\w+)(,\\w+)*\\)\\.$";

  @Override
  public boolean isValid(String fact) {
    String factWithoutWhitespace = fact.replaceAll("\\s", "");
    return Pattern.matches(FACT_REGEX, factWithoutWhitespace);
  }

}
