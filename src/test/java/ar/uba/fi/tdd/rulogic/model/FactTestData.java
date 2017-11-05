package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;

public final class FactTestData {

  private FactTestData() {}

  public static final String FACT_1_STRING = "varon(juan).";
  public static final Fact FACT_1 = new Fact("varon", ImmutableList.of("juan"));

  public static final String FACT_2_STRING = "padre(juan, pepe).";
  public static final Fact FACT_2 = new Fact("varon", ImmutableList.of("juan"));

  public static final String INVALID_FACT_STRING = "varon.";

}
