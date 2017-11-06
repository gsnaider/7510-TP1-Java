package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.Before;
import org.junit.Test;
import ar.uba.fi.tdd.rulogic.knowledgebase.QueryParser;
import ar.uba.fi.tdd.rulogic.model.Query;
import ar.uba.fi.tdd.rulogic.model.QueryTestData;
import ar.uba.fi.tdd.rulogic.validator.QueryValidator;

public class QueryParserIntegrationTest {

  private QueryParser queryParser;

  @Before
  public void setUp() {
    Validator<String> queryValidator = new QueryValidator();
    queryParser = new QueryParserImpl(queryValidator);
  }

  @Test
  public void testParseQuery_ValidQuery_ReturnsQuery() {
    Query query = queryParser.parseQuery(QueryTestData.QUERY_1_STRING);
    assertThat(query).isEqualTo(QueryTestData.QUERY_1);

    query = queryParser.parseQuery(QueryTestData.QUERY_2_STRING);
    assertThat(query).isEqualTo(QueryTestData.QUERY_2);
  }

  @Test
  public void testParseQuery_InvalidQuery_ThrowsIllegalArgumentException() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      queryParser.parseQuery(QueryTestData.INVALID_QUERY_STRING);
    }).withMessageContaining(QueryTestData.INVALID_QUERY_STRING);
  }
}
