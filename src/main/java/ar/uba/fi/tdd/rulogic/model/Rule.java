package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.gson.GsonBuilder;

public final class Rule extends Statement {

  private final ImmutableSet<Statement> statements;

  private Rule(Builder builder) {
    super(builder.name, builder.parameters);
    if (builder.statements == null) {
      throw new NullPointerException();
    }
    this.statements = builder.statements;
  }

  @Override
  public void addToDatabase(DatabaseImpl.Builder databaseBuilder) {
    databaseBuilder.addStatement(this);
  }

  public ImmutableSet<Statement> getStatements() {
    return statements;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name;
    private ImmutableList<String> parameters;
    private ImmutableSet<Statement> statements;

    public Rule build() {
      return new Rule(this);
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder parameters(ImmutableList<String> parameters) {
      this.parameters = parameters;
      return this;
    }

    public Builder statements(ImmutableSet<Statement> statements) {
      this.statements = statements;
      return this;
    }
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

}
