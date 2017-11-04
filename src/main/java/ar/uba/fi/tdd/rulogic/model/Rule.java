package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class Rule extends Statement {

  private ImmutableSet<Statement> statements;

  public Rule(String name, ImmutableList<String> parameters, ImmutableSet<Statement> statements) {
    super(name, parameters);
    this.statements = statements;
  }

  public ImmutableSet<Statement> getStatements() {
    return statements;
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



}
