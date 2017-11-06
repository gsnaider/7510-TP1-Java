package ar.uba.fi.tdd.rulogic.parser;

import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Statement;

public class RuleParserImpl implements RuleParser {

  private static final String RULE_ASSIGN_CODE = ":-";
  private static final String RULE_STATEMENTS_SEPARATOR_REGEX = ",(?![^\\(]*\\))";

  private RuleValidator ruleValidator;

  public RuleParserImpl(RuleValidator ruleValidator) {
    this.ruleValidator = ruleValidator;
  }

  @Override
  public Rule parseRule(String ruleLine) {
    if (!ruleValidator.isValid(ruleLine)) {
      throw new IllegalArgumentException(String.format("Invalid rule: %s", ruleLine));
    }
    Rule rule = Rule
        .builder()
        .name(ParserUtil.parseName(ruleLine))
        .parameters(ParserUtil.parseParameters(ruleLine))
        .statements(parseStatements(ruleLine))
        .build();
    if (!ruleValidator.hasValidParameters(rule)) {
      throw new IllegalArgumentException(String.format("Invalid rule: %s", ruleLine));
    }
    return rule;
  }

  private ImmutableSet<Statement> parseStatements(String ruleLine) {
    String[] statementsArray =
        ruleLine.split(RULE_ASSIGN_CODE)[1].split(RULE_STATEMENTS_SEPARATOR_REGEX);

    ImmutableSet.Builder<Statement> statementsSetBuilder = ImmutableSet.builder();
    for (String statement : statementsArray) {
      statementsSetBuilder.add(
          new Statement(ParserUtil.parseName(statement), ParserUtil.parseParameters(statement)));
    }

    return statementsSetBuilder.build();
  }

}
