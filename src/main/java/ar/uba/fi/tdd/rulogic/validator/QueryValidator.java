package ar.uba.fi.tdd.rulogic.validator;

import java.util.regex.Pattern;
import ar.uba.fi.tdd.rulogic.parser.Validator;

public class QueryValidator implements Validator<String> {

  private static final String QUERY_REGEX = "^\\w+\\((\\w+)(,\\w+)*\\)$";

  @Override
  public boolean isValid(String query) {
    String queryWithoutWhitespace = query.replaceAll("\\s", "");
    return Pattern.matches(QUERY_REGEX, queryWithoutWhitespace);
  }

}
