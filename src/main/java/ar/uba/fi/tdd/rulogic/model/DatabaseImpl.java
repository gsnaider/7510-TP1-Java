package ar.uba.fi.tdd.rulogic.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.GsonBuilder;
import ar.uba.fi.tdd.rulogic.knowledgebase.Database;

public final class DatabaseImpl implements Database {

  private final ImmutableSet<Statement> statements;
  private final ImmutableMap<String, Rule> rules;

  private DatabaseImpl(Builder builder) {
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

  @Override
  public boolean contains(Statement statement) {
    if (statements.contains(statement)) {
      return true;
    } else {
      return containsRule(statement);
    }
  }

  private boolean containsRule(Statement statement) {
    Rule rule = findRule(statement);
    if (rule == null) {
      return false;
    }
    return containsRuleStatements(rule, statement);
  }

  private boolean containsRuleStatements(Rule rule, Statement statement) {
    ImmutableMap<String, String> parametersMap = getRuleParametersMap(rule, statement);
    for (Statement ruleStatement : rule.getStatements()) {
      ImmutableList.Builder<String> statementReplacedParametersBuilder = ImmutableList.builder();
      for (String parameter : ruleStatement.getParameters()) {
        statementReplacedParametersBuilder.add(parametersMap.get(parameter));
      }
      Statement statementWithReplacedParameters =
          new Statement(ruleStatement.getName(), statementReplacedParametersBuilder.build());
      if (!contains(statementWithReplacedParameters)) {
        return false;
      }
    }
    return true;
  }

  private ImmutableMap<String, String> getRuleParametersMap(Rule rule, Statement statement) {
    ImmutableMap.Builder<String, String> parametersMapBuilder = ImmutableMap.builder();
    Iterator<String> ruleParametersIterator = rule.getParameters().iterator();
    Iterator<String> queryParametersIterator = statement.getParameters().iterator();
    while (ruleParametersIterator.hasNext() && queryParametersIterator.hasNext()) {
      parametersMapBuilder.put(ruleParametersIterator.next(), queryParametersIterator.next());
    }
    return parametersMapBuilder.build();
  }

  private Rule findRule(Statement statement) {
    if (!rules.containsKey(statement.getName())) {
      return null;
    }
    Rule rule = rules.get(statement.getName());
    if (rule.getParameters().size() != statement.getParameters().size()) {
      return null;
    }
    return rule;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Set<Statement> statements = new HashSet<>();
    private Set<Rule> rules = new HashSet<>();

    public DatabaseImpl build() {
      return new DatabaseImpl(this);
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
      statements.add(rule);
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
    DatabaseImpl other = (DatabaseImpl) obj;
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

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

}
