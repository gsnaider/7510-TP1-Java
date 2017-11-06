package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;

public final class QueryTestData {

  private QueryTestData() {}

  public static final String QUERY_1_STRING = "varon(juan)";
  public static final Query QUERY_1 =
      Query.builder().name("varon").parameters(ImmutableList.of("juan")).build();

  public static final String QUERY_2_STRING = "padre(juan, pepe)";
  public static final Query QUERY_2 =
      Query.builder().name("padre").parameters(ImmutableList.of("juan", "pepe")).build();

  public static final String INVALID_QUERY_STRING = "varon";

}
