package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public final class RuleTestData {

  private RuleTestData() {}

  public static final String RULE_STRING = "hijo(X, Y) :- varon(X), padre(Y, X).";
  public static final Rule RULE = rule();

  public static final String INVALID_RULE_STRING = "hijo(X) :- varon(Y).";

  private static Rule rule() {
    String ruleName = "hijo";
    ImmutableList<String> ruleParams = ImmutableList.of("X", "Y");
    ImmutableSet<Statement> ruleStatements =
        ImmutableSet.of(FactTestData.FACT_1, FactTestData.FACT_2);
    return new Rule(ruleName, ruleParams, ruleStatements);
  }

}
