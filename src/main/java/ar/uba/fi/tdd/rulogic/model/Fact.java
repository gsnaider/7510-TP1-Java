package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.gson.GsonBuilder;

public final class Fact extends Statement {

  private Fact(Builder builder) {
    super(builder.name, builder.parameters);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name;
    private ImmutableList<String> parameters;

    public Fact build() {
      return new Fact(this);
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
