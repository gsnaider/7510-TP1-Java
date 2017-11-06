package ar.uba.fi.tdd.rulogic;

import java.io.FileNotFoundException;
import java.util.Scanner;
import ar.uba.fi.tdd.rulogic.databasereader.DatabaseReaderImpl;
import ar.uba.fi.tdd.rulogic.databasereader.StatementParser;
import ar.uba.fi.tdd.rulogic.knowledgebase.DatabaseReader;
import ar.uba.fi.tdd.rulogic.knowledgebase.IllegalDatabaseFormatException;
import ar.uba.fi.tdd.rulogic.knowledgebase.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.parser.FactParser;
import ar.uba.fi.tdd.rulogic.parser.FactParserImpl;
import ar.uba.fi.tdd.rulogic.parser.QueryParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleParser;
import ar.uba.fi.tdd.rulogic.parser.RuleParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleValidator;
import ar.uba.fi.tdd.rulogic.parser.StatementParserImpl;
import ar.uba.fi.tdd.rulogic.parser.Validator;
import ar.uba.fi.tdd.rulogic.validator.FactValidator;
import ar.uba.fi.tdd.rulogic.validator.QueryValidator;
import ar.uba.fi.tdd.rulogic.validator.RuleValidatorImpl;

/**
 * Console application.
 *
 */
public class App {

  private static final String DATABASE_PATH = "rules.db";

  public static void main(String[] args) throws Exception {
    System.out.println("I shall answer all your questions!");
    KnowledgeBase knowledgeBase;
    try {
      knowledgeBase = createKnowledgeBase();
    } catch (Exception e) {
      System.err.println(String.format("Error initializing database: %s", e.getMessage()));
      return;
    }
    Scanner scanner = new Scanner(System.in);
    System.out.println("Type 'exit' to exit.");
    System.out.println("Enter a query:");
    String query = scanner.nextLine();
    while (!query.equals("exit")) {
      try {
        if (knowledgeBase.answer(query)) {
          System.out.println("YES");
        } else {
          System.out.println("NO");
        }
      } catch (Exception e) {
        System.err.println(String.format("Error processing query: %s", e.getMessage()));
      }
      System.out.println("Enter a query:");
      query = scanner.nextLine();
    }
    System.out.println("Good bye!");
    scanner.close();
  }

  private static KnowledgeBase createKnowledgeBase()
      throws FileNotFoundException, IllegalDatabaseFormatException {
    Validator<String> factValidator = new FactValidator();
    FactParser factParser = new FactParserImpl(factValidator);
    RuleValidator ruleValidator = new RuleValidatorImpl();
    RuleParser ruleParser = new RuleParserImpl(ruleValidator);
    StatementParser statementParser = new StatementParserImpl(factParser, ruleParser);
    DatabaseReader databaseReader = new DatabaseReaderImpl(statementParser);
    Validator<String> queryValidator = new QueryValidator();
    return new KnowledgeBase(databaseReader.readDatabase(DATABASE_PATH),
        new QueryParserImpl(queryValidator));
  }

}
