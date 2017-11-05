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
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((statements == null) ? 0 : statements.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Rule other = (Rule) obj;
    if (statements == null) {
      if (other.statements != null)
        return false;
    } else if (!statements.equals(other.statements))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

}
