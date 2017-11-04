package ar.uba.fi.tdd.rulogic.model;

import com.google.common.collect.ImmutableList;

/**
 * Entity represented by a name and one or more parameters.
 */
public abstract class Statement {

  private final String name;
  private final ImmutableList<String> parameters;

  public Statement(String name, ImmutableList<String> parameters) {
    this.name = name;
    this.parameters = parameters;
  }

  public String getName() {
    return name;
  }

  public ImmutableList<String> getParameters() {
    return parameters;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
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
    Statement other = (Statement) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (parameters == null) {
      if (other.parameters != null)
        return false;
    } else if (!parameters.equals(other.parameters))
      return false;
    return true;
  }

}
