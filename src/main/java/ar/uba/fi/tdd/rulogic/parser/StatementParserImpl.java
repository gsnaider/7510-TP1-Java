package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.databasereader.StatementParser;
import ar.uba.fi.tdd.rulogic.model.Statement;

public class StatementParserImpl implements StatementParser {

  private static final String RULE_ASSIGN_CODE = ":-";

  private final FactParser factParser;
  private final RuleParser ruleParser;

  public StatementParserImpl(FactParser factParser, RuleParser ruleParser) {
    this.factParser = factParser;
    this.ruleParser = ruleParser;
  }

  @Override
  public Statement parseStatement(String statementLine) {
    if (statementLine.contains(RULE_ASSIGN_CODE)) {
      return ruleParser.parseRule(statementLine);
    } else {
      return factParser.parseFact(statementLine);
    }
  }

}
