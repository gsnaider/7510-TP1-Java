package ar.uba.fi.tdd.rulogic.database;

import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Statement;

public final class Database {

  private final ImmutableSet<Statement> statements;
  private final ImmutableMap<String, Rule> rules;

  private Database(Builder builder) {
    if (builder.statements == null || builder.rules == null) {
      throw new NullPointerException();
    }
    this.statements = ImmutableSet.copyOf(builder.statements);
    ImmutableMap.Builder<String, Rule> rulesMapBuilder = ImmutableMap.builder();
    for (Rule rule : builder.rules) {
      rulesMapBuilder.put(rule.getName(), rule);
    }
    this.rules = rulesMapBuilder.build();
  }

  public boolean contains(Statement statement) {
    // TODO implement.
    return false;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Set<Statement> statements = new HashSet<>();
    private Set<Rule> rules = new HashSet<>();

    public Database build() {
      return new Database(this);
    }

    public Builder statements(Set<Statement> statements) {
      this.statements = statements;
      return this;
    }

    public Builder rules(Set<Rule> rules) {
      this.rules = rules;
      return this;
    }

    public Builder addStatement(Statement statement) {
      statements.add(statement);
      return this;
    }

    public Builder addStatement(Rule rule) {
      rules.add(rule);
      return this;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((statements == null) ? 0 : statements.hashCode());
    result = prime * result + ((rules == null) ? 0 : rules.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Database other = (Database) obj;
    if (statements == null) {
      if (other.statements != null)
        return false;
    } else if (!statements.equals(other.statements))
      return false;
    if (rules == null) {
      if (other.rules != null)
        return false;
    } else if (!rules.equals(other.rules))
      return false;
    return true;
  }

}
