package ar.uba.fi.tdd.rulogic.database;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Statement;

public class Database {

  private final ImmutableSet<Fact> facts;
  private final ImmutableMap<String, Rule> rules;

  public Database(ImmutableSet<Fact> facts, ImmutableSet<Rule> rules) {
    this.facts = facts;
    ImmutableMap.Builder<String, Rule> rulesMapBuilder = ImmutableMap.builder();
    for (Rule rule : rules) {
      rulesMapBuilder.put(rule.getName(), rule);
    }
    this.rules = rulesMapBuilder.build();
  }

  public boolean contains(Statement statement) {
    // TODO
    return false;
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
