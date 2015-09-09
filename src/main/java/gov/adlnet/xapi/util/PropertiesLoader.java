package gov.adlnet.xapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader
{
    Properties properties = new Properties();

    public Properties load() throws IOException
    {
        if (properties.isEmpty())
        {
            InputStream stream = null;
            try
            {
                stream = this.getClass().getClassLoader().getResourceAsStream("lrs.properties");
                
                if (stream == null)
                {
                    stream = this.getClass().getClassLoader().getResourceAsStream(
                        "lrs_default.properties");
                }
                
                properties.load(stream);
            }
            finally
            {
                if (stream != null)
                {
                    stream.close();
                }
            }
        }
        return properties;
    }
}
