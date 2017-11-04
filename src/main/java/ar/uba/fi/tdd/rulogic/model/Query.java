package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;

public class Query extends Statement {

  public Query(String name, ImmutableList<String> parameters) {
    super(name, parameters);
  }

}
