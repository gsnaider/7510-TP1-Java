package ar.uba.fi.tdd.rulogic.validator;

import java.util.regex.Pattern;
import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Statement;
import ar.uba.fi.tdd.rulogic.parser.RuleValidator;

public class RuleValidatorImpl implements RuleValidator {

  // Matches the format:
  // "name(param1, param2):-statement1(param1, param2),statement2(param1, param2)."
  // (with one or multiple parameters and statements)
  private static final String RULE_REGEX =
      "^\\w+\\((\\w+)(,\\w+)*\\):-(\\w+\\((\\w+)(,\\w+)*\\))(,\\w+\\((\\w+)(,\\w+)*\\))*\\.$";

  @Override
  public boolean isValid(String rule) {
    String ruleWithoutWhitespace = rule.replaceAll("\\s", "");
    return Pattern.matches(RULE_REGEX, ruleWithoutWhitespace);
  }

  @Override
  public boolean hasValidParameters(Rule rule) {
    ImmutableSet<String> ruleParameters = ImmutableSet.copyOf(rule.getParameters());
    ImmutableSet.Builder<String> ruleStatementsParametersBuilder = ImmutableSet.builder();
    for (Statement statement : rule.getStatements()) {
      ruleStatementsParametersBuilder.addAll(statement.getParameters());
    }
    return ruleParameters.equals(ruleStatementsParametersBuilder.build());
  }

}
