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
                "{\"message\":\"ok\"}")));

        String result = client.getAggregate("[{\"$match\":{\"statement.timestamp\":{\"$gt\":\"2015-09-22T00:00\",\"$lt\":\"2015-09-23T00:00\"}}}]");

        assertNotNull(result);
        assertEquals("{\"message\":\"ok\"}", result);

        verify(getRequestedFor(urlPathMatching("/api/v1/statements/aggregate*")).withQueryParam(
            "pipeline",
            containing("{\"$match\":{\"statement.timestamp\":{\"$gt\":\"2015-09-22T00:00\",\"$lt\":\"2015-09-23T00:00\"}}}")));
    }
}
