package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.database.Database;

public final class TestData {

  private TestData() {}

  public static final Database EMPTY_DATABASE = new Database(ImmutableSet.of(), ImmutableSet.of());

  public static final Database SMALL_DATABASE = smallDatabase();

  private static Database smallDatabase() {
    ImmutableSet<Fact> facts = ImmutableSet.of(
        new Fact("varon", ImmutableList.of("juan")),
        new Fact("padre", ImmutableList.of("juan", "pepe")));

    String ruleName = "hijo";
    ImmutableList<String> ruleParams = ImmutableList.of("X", "Y");
    ImmutableSet<Statement> ruleStatements = ImmutableSet.of(
        new Fact("varon", ImmutableList.of("X")),
        new Fact("padre", ImmutableList.of("Y", "X")));
    ImmutableSet<Rule> rules = ImmutableSet.of(new Rule(ruleName, ruleParams, ruleStatements));

    return new Database(facts, rules);
  }


}
