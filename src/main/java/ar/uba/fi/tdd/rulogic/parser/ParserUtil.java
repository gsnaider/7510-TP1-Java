package ar.uba.fi.tdd.rulogic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.ImmutableList;

final class ParserUtil {

  private ParserUtil() {}

  private static final String NAME_REGEX = "^\\w+";
  private static final String PARAMETERS_REGEX = "\\([^\\)]*\\)";

  /**
   * Returns the name of a valid {@link Statement} String.
   * 
   * @throws IllegalArgumentException if the {@code statement} is not valid
   */
  static String parseName(String statement) {
    Pattern namePattern = Pattern.compile(NAME_REGEX);
    Matcher matcher = namePattern.matcher(statement.trim());
    if (!matcher.find()) {
      throw new IllegalArgumentException(String.format("Invalid statement: %s", statement));
    }
    return matcher.group();
  }

  /**
   * Returns the name of a valid {@link Statement} String.
   * 
   * @throws IllegalArgumentException if the {@code statement} is not valid
   */
  static ImmutableList<String> parseParameters(String statement) {
    Pattern parametersPattern = Pattern.compile(PARAMETERS_REGEX);
    Matcher matcher = parametersPattern.matcher(statement.trim());
    if (!matcher.find()) {
      throw new IllegalArgumentException(String.format("Invalid statement: %s", statement));
    }
    String[] parametersArray = matcher.group().replace("(", "").replace(")", "").split(",");
    ImmutableList.Builder<String> parametersListBuilder = ImmutableList.builder();
    for (String parameter : parametersArray) {
      parametersListBuilder.add(parameter.trim());
    }
    return parametersListBuilder.build();
  }

}
