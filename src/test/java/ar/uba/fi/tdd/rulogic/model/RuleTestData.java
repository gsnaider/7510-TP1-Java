package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public final class RuleTestData {

  private RuleTestData() {}

  public static final String RULE_STRING = "hijo(X, Y) :- varon(X), padre(Y, X).";
  public static final Rule RULE = rule();

  public static final String INVALID_RULE_STRING = "hijo(X) :- varon(Y).";

  private static Rule rule() {
    String name = "hijo";
    ImmutableList<String> parameters = ImmutableList.of("X", "Y");
    ImmutableSet<Statement> statements = ImmutableSet.of(
        Fact.builder().name("varon").parameters(ImmutableList.of("X")).build(),
        Fact.builder().name("padre").parameters(ImmutableList.of("Y", "X")).build());
    return Rule.builder().name(name).parameters(parameters).statements(statements).build();
  }

}
