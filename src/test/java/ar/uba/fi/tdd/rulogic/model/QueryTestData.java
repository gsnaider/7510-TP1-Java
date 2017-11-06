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

  public static final String QUERY_3_STRING = "hijo(pepe, juan)";
  public static final Query QUERY_3 =
      Query.builder().name("hijo").parameters(ImmutableList.of("pepe", "juan")).build();

  public static final Query NON_PRESENT_QUERY_1 =
      Query.builder().name("varon").parameters(ImmutableList.of("maria")).build();
  public static final Query NON_PRESENT_QUERY_2 =
      Query.builder().name("hijo").parameters(ImmutableList.of("roberto", "mario")).build();
  public static final Query NON_PRESENT_QUERY_3 =
      Query.builder().name("hija").parameters(ImmutableList.of("maria", "pepe")).build();

  public static final String INVALID_QUERY_STRING = "varon";

}
