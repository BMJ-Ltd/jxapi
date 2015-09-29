package gov.adlnet.xapi;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.adlnet.xapi.client.AggregateClient;
import gov.adlnet.xapi.model.Activity;
import gov.adlnet.xapi.model.Aggregate;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class AggregateClientTest
{
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(40998);

    private static final String LRS_URI = "http://localhost:40998";
    private static final String USERNAME = "foo";
    private static final String PASSWORD = "roo";

    @Test
    public void testGetAggregate() throws IOException
    {
        AggregateClient client = new AggregateClient(LRS_URI, USERNAME, PASSWORD);

        stubFor(get(urlPathMatching("/api/v1/statements/aggregate*")).willReturn(
            aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(
                "{\"result\":[{\"statement\":{\"version\":\"1.0.0\",\"verb\":{\"id\":\"http://adlnet.gov/expapi/verbs/experienced\","
                    + "\"display\": {\"en-US\": \"experienced\"}},\"actor\":{\"objectType\":\"Agent\",\"name\":\"foo\","
                    + "\"account\":{\"homePage\":\"http://myaccount.bmj.com\",\"name\": \"id111122\"}},\"object\":{\"objectType\":\"Activity\","
                    + "\"id\":\"http://foo.html\",\"description\":{\"en-GB\":\"roo\"}}},\"authority\":{\"objectType\":\"Agent\","
                    + "\"name\":\"webanywhere_BMJ\",\"mbox\":\"mailto:hello@learninglocker.net\"},\"stored\":\"2015-09-22T12:47:32.536700+00:00\","
                    + "\"timestamp\": \"2015-09-22T12:47:32.536700+00:00\",\"id\":\"d62d39e6-a71e-4dac-b095-d7f52499cd9f\"}],\"ok\":1}")));

        Aggregate result = client.getAggregate("[{\"$match\":{\"statement.timestamp\":{\"$gt\":\"2015-09-22T00:00\",\"$lt\":\"2015-09-23T00:00\"}}}]");

        assertNotNull(result);
        assertEquals("1", result.getOk());
        assertEquals("foo", result.getResult().get(0).getStatement().getActor().getName());
        assertEquals("http://adlnet.gov/expapi/verbs/experienced",
            result.getResult().get(0).getStatement().getVerb().getId());
        assertEquals("http://foo.html",
            ((Activity) result.getResult().get(0).getStatement().getObject()).getId());

        verify(getRequestedFor(urlPathMatching("/api/v1/statements/aggregate*")).withQueryParam(
            "pipeline",
            containing("{\"$match\":{\"statement.timestamp\":{\"$gt\":\"2015-09-22T00:00\",\"$lt\":\"2015-09-23T00:00\"}}}")));
    }
}
