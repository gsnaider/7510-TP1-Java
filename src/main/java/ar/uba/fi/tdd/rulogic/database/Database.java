package ar.uba.fi.tdd.rulogic.database;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Statement;

public final class Database {

  private final ImmutableSet<Fact> facts;
  private final ImmutableMap<String, Rule> rules;

  private Database(Builder builder) {
    if (builder.facts == null || builder.rules == null) {
      throw new NullPointerException();
    }
    this.facts = builder.facts;
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
    private ImmutableSet<Fact> facts;
    private ImmutableSet<Rule> rules;

    public Database build() {
      return new Database(this);
    }

    public Builder facts(ImmutableSet<Fact> facts) {
      this.facts = facts;
      return this;
    }

    public Builder rules(ImmutableSet<Rule> rules) {
      this.rules = rules;
      return this;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((facts == null) ? 0 : facts.hashCode());
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
    if (facts == null) {
      if (other.facts != null)
        return false;
    } else if (!facts.equals(other.facts))
      return false;
    if (rules == null) {
      if (other.rules != null)
        return false;
    } else if (!rules.equals(other.rules))
      return false;
    return true;
  }

}
