package ar.uba.fi.tdd.rulogic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.ImmutableList;
import ar.uba.fi.tdd.rulogic.model.Fact;

public class FactParserImpl implements FactParser {

  private static final String NAME_REGEX = "^\\w+";
  private static final String PARAMETERS_REGEX = "\\(\\w+(,\\w+)*\\)";
  private static final String FACT_REGEX =
      "(" + NAME_REGEX + ")" + "(" + PARAMETERS_REGEX + ")" + ".";

  @Override
  public Fact parseFact(String fact) {
    String factWithoutWhitespace = fact.replaceAll("\\s", "");

    Pattern pattern = Pattern.compile(FACT_REGEX);
    Matcher matcher = pattern.matcher(factWithoutWhitespace);
    if (!matcher.matches()) {
      throw new IllegalArgumentException(String.format("Invalid fact: %s", fact));
    }
    return Fact
        .builder()
        .name(parseName(factWithoutWhitespace, matcher))
        .parameters(parseParameters(factWithoutWhitespace, matcher))
        .build();
  }

  private String parseName(String fact, Matcher matcher) {
    return matcher.group(1);
  }

  private ImmutableList<String> parseParameters(String fact, Matcher matcher) {
    String[] parametersArray = matcher.group(2).replace("(", "").replace(")", "").split(",");
    ImmutableList.Builder<String> parametersListBuilder = ImmutableList.builder();
    for (String parameter : parametersArray) {
      parametersListBuilder.add(parameter.trim());
    }
    return parametersListBuilder.build();
  }

}
