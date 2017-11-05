package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.database.Database;

public final class DatabaseTestData {

  private DatabaseTestData() {}

  public static final String EMPTY_DATABASE_PATH = "empty.db";
  public static final Database EMPTY_DATABASE = new Database(ImmutableSet.of(), ImmutableSet.of());

  public static final String SMALL_DATABASE_PATH = "small.db";
  public static final Database SMALL_DATABASE = smallDatabase();

  public static final String INVALID_RULE_DATABASE_PATH = "invalid-rule.db";
  public static final String INVALID_FACT_DATABASE_PATH = "invalid-fact.db";

  private static Database smallDatabase() {
    ImmutableSet<Fact> facts = ImmutableSet.of(FactTestData.FACT_1, FactTestData.FACT_2);
    ImmutableSet<Rule> rules = ImmutableSet.of(RuleTestData.RULE);
    return new Database(facts, rules);
  }

}
