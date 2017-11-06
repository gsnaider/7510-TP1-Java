package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.gson.GsonBuilder;

public final class Query extends Statement {

  private Query(Builder builder) {
    super(builder.name, builder.parameters);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name;
    private ImmutableList<String> parameters;

    public Query build() {
      return new Query(this);
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder parameters(ImmutableList<String> parameters) {
      this.parameters = parameters;
      return this;
    }
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

}
