package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.database.Database;

public final class TestData {

  private TestData() {}

  public static final Database EMPTY_DATABASE = new Database(ImmutableSet.of(), ImmutableSet.of());

  public static final Database SMALL_DATABASE =
      new Database(
          ImmutableSet.of(
            new Fact("varon", ImmutableList.of("juan")),
            new Fact("padre", ImmutableList.of("juan", "pepe"))),
          ImmutableSet.of(
              new Rule(
                "hijo", ImmutableList.of("X", "Y"), ImmutableSet.of(
                new Fact("varon", ImmutableList.of("X")),
                new Fact("padre", ImmutableList.of("Y", "X"))))));

}
