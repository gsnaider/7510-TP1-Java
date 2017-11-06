package ar.uba.fi.tdd.rulogic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.ImmutableList;
import ar.uba.fi.tdd.rulogic.model.Fact;

public class FactParserImpl implements FactParser {

  private final Validator<String> factValidator;

  public FactParserImpl(Validator<String> factValidator) {
    this.factValidator = factValidator;
  }

  private static final String NAME_REGEX = "^\\w+";
  private static final String PARAMETERS_REGEX = "\\([^\\)]*\\)";

  @Override
  public Fact parseFact(String fact) {
    if (!factValidator.isValid(fact)) {
      throwInvalidFactException(fact);
    }
    return Fact.builder().name(parseName(fact)).parameters(parseParameters(fact)).build();
  }

  private static String parseName(String fact) {
    Pattern namePattern = Pattern.compile(NAME_REGEX);
    Matcher matcher = namePattern.matcher(fact.trim());
    // We have to call matcher.find() in order to be able to use the matcher.group method
    // afterwards, even tough we have already validated the Fact.
    if (!matcher.find()) {
      throwInvalidFactException(fact);
    }
    return matcher.group();
  }

  private static ImmutableList<String> parseParameters(String fact) {
    Pattern parametersPattern = Pattern.compile(PARAMETERS_REGEX);
    Matcher matcher = parametersPattern.matcher(fact.trim());
    // We have to call matcher.find() in order to be able to use the matcher.group method
    // afterwards, even tough we have already validated the Fact.
    if (!matcher.find()) {
      throwInvalidFactException(fact);
    }
    String[] parametersArray = matcher.group().replace("(", "").replace(")", "").split(",");
    ImmutableList.Builder<String> parametersListBuilder = ImmutableList.builder();
    for (String parameter : parametersArray) {
      parametersListBuilder.add(parameter.trim());
    }
    return parametersListBuilder.build();
  }

  private static void throwInvalidFactException(String fact) {
    throw new IllegalArgumentException(String.format("Invalid fact: %s", fact));
  }

}
