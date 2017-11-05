package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.database.Database;

public final class DatabaseTestData {

  private DatabaseTestData() {}

  public static final String EMPTY_DATABASE_PATH = "empty.db";
  public static final Database EMPTY_DATABASE =
      Database.builder().statements(ImmutableSet.of()).rules(ImmutableSet.of()).build();

  public static final String SMALL_DATABASE_PATH = "small.db";
  public static final Database SMALL_DATABASE = Database
      .builder()
      .statements(ImmutableSet.of(FactTestData.FACT_1, FactTestData.FACT_2))
      .rules(ImmutableSet.of(RuleTestData.RULE))
      .build();

  public static final String INVALID_RULE_DATABASE_PATH = "invalid-rule.db";
  public static final String INVALID_FACT_DATABASE_PATH = "invalid-fact.db";

}
