package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;

public class Fact extends Statement {

  public Fact(String name, ImmutableList<String> parameters) {
    super(name, parameters);
  }

}
