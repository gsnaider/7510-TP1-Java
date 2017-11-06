package ar.uba.fi.tdd.rulogic.knowledgebase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.databasereader.DatabaseReaderImpl;
import ar.uba.fi.tdd.rulogic.databasereader.StatementParser;
import ar.uba.fi.tdd.rulogic.parser.FactParser;
import ar.uba.fi.tdd.rulogic.parser.FactParserImpl;
import ar.uba.fi.tdd.rulogic.parser.QueryParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleParser;
import ar.uba.fi.tdd.rulogic.parser.RuleParserImpl;
import ar.uba.fi.tdd.rulogic.parser.RuleValidator;
import ar.uba.fi.tdd.rulogic.parser.StatementParserImpl;
import ar.uba.fi.tdd.rulogic.parser.Validator;
import ar.uba.fi.tdd.rulogic.validator.FactValidator;
import ar.uba.fi.tdd.rulogic.validator.RuleValidatorImpl;

public class ParentDatabaseTest {

  private static final String PARENT_DATABASE_PATH = "parent.db";

  private KnowledgeBase knowledgeBase;

  @Before
  public void setUp() throws Exception {
    Validator<String> factValidator = new FactValidator();
    FactParser factParser = new FactParserImpl(factValidator);
    RuleValidator ruleValidator = new RuleValidatorImpl();
    RuleParser ruleParser = new RuleParserImpl(ruleValidator);
    StatementParser statementParser = new StatementParserImpl(factParser, ruleParser);
    DatabaseReader databaseReader = new DatabaseReaderImpl(statementParser);
    knowledgeBase =
        new KnowledgeBase(databaseReader.readDatabase(PARENT_DATABASE_PATH), new QueryParserImpl());
  }

  @Test
  public void testAnswer_PresentFact_ReturnsTrue() {
    assertThat(knowledgeBase.answer("varon(juan)")).isTrue();
    assertThat(knowledgeBase.answer("mujer(maria)")).isTrue();
    assertThat(knowledgeBase.answer("padre(juan, pepe)")).isTrue();
    assertThat(knowledgeBase.answer("padre(roberto, cecilia)")).isTrue();
    assertThat(knowledgeBase.answer("hermano(nicolas, roberto)")).isTrue();
  }

  @Test
  public void testAnswer_NonPresentFact_ReturnsFalse() {
    assertThat(knowledgeBase.answer("mujer(juan)")).isFalse();
    assertThat(knowledgeBase.answer("varon(maria)")).isFalse();
    assertThat(knowledgeBase.answer("padre(pepe, juan)")).isFalse();
    assertThat(knowledgeBase.answer("padre(hector, cecilia)")).isFalse();
    assertThat(knowledgeBase.answer("hermano(pepe, juan)")).isFalse();
    assertThat(knowledgeBase.answer("hermana(maria, juan)")).isFalse();
  }

  @Test
  public void testAnswer_PresentRule_ReturnsTrue() {
    assertThat(knowledgeBase.answer("hijo(pepe, juan)")).isTrue();
    assertThat(knowledgeBase.answer("hija(maria, hector)")).isTrue();
    assertThat(knowledgeBase.answer("tio(nicolas, alejandro, roberto)")).isTrue();
  }

  @Test
  public void testAnswer_NonPresentRule_ReturnsFalse() {
    assertThat(knowledgeBase.answer("hijo(pepe, hector)")).isFalse();
    assertThat(knowledgeBase.answer("hija(maria, roberto)")).isFalse();
    assertThat(knowledgeBase.answer("tia(julia, alejandro, roberto)")).isFalse();
  }

  @Test
  public void testAnswer_InvalidQuery_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("varon");
    }).withMessageContaining("Invalid query");

    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("varon()");
    }).withMessageContaining("Invalid query");

    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("varon(juan).");
    }).withMessageContaining("Invalid query");

    assertThatIllegalArgumentException().isThrownBy(() -> {
      knowledgeBase.answer("padre(juan,)");
    }).withMessageContaining("Invalid query");
  }

}
