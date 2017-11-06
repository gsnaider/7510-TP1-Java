package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;

public final class FactTestData {

  private FactTestData() {}

  public static final String FACT_1_STRING = "varon(juan).";
  public static final Fact FACT_1 =
      Fact.builder().name("varon").parameters(ImmutableList.of("juan")).build();

  public static final String FACT_2_STRING = "padre(juan, pepe).";
  public static final String FACT_2_STRING_WITH_WHITESPACE = "  padre   (  juan  ,   pepe  )  .  ";
  public static final Fact FACT_2 =
      Fact.builder().name("padre").parameters(ImmutableList.of("juan", "pepe")).build();

  public static final String INVALID_FACT_STRING = "varon.";

  public static final ImmutableList<String> INVALID_FACTS = ImmutableList.of(
      "varon",
      "varon.",
      "varon().",
      "varon(juan)",
      "varon(juan,).",
      "varon(juan.",
      "(juan).");
}
