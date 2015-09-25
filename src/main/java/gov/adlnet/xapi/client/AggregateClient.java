package gov.adlnet.xapi.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Client to make use of the aggregation capabilities of the LRS by using a MongoDB pipeline
 */
public class AggregateClient extends BaseClient
{
    private static final String UTF_8 = "UTF-8";

    public AggregateClient(String uri, String username, String password)
        throws MalformedURLException
    {
        super(uri, username, password);
    }

    public AggregateClient(URL uri, String username, String password) throws MalformedURLException
    {
        super(uri, username, password);
    }

    /**
     * Get the result of the aggregation by passing a pipeline in a JSON format.
     * 
     * @param pipeline the pipeline in JSON format
     * @return a String containing a JSON object with the result
     * @throws IOException if the request did not work
     */
    public String getAggregate(String pipeline) throws IOException
    {
        String path = "/api/v1/statements/aggregate?pipeline=" + URLEncoder.encode(pipeline, UTF_8);
        return issueGet(path);
    }
}
