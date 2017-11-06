package ar.uba.fi.tdd.rulogic.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.uba.fi.tdd.rulogic.knowledgebase.QueryParser;
import ar.uba.fi.tdd.rulogic.model.Query;
import ar.uba.fi.tdd.rulogic.model.QueryTestData;

public class QueryParserTest {

  private QueryParser queryParser;

  @Mock
  private Validator<String> queryValidator;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    queryParser = new QueryParserImpl(queryValidator);
  }

  @Test
  public void testParseQuery_ValidQuery_ReturnsQuery() {
    when(queryValidator.isValid(anyString())).thenReturn(true);

    Query query = queryParser.parseQuery(QueryTestData.QUERY_1_STRING);
    assertThat(query).isEqualTo(QueryTestData.QUERY_1);

    query = queryParser.parseQuery(QueryTestData.QUERY_2_STRING);
    assertThat(query).isEqualTo(QueryTestData.QUERY_2);

  }

  @Test
  public void testParseQuery_InvalidQuery_ThrowsIllegalArgumentException() {
    when(queryValidator.isValid(anyString())).thenReturn(false);
    assertThatIllegalArgumentException().isThrownBy(() -> {
      queryParser.parseQuery(QueryTestData.INVALID_QUERY_STRING);
    }).withMessageContaining(QueryTestData.INVALID_QUERY_STRING);
  }
}
