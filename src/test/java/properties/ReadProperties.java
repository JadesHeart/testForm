package properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * класс проперти
 */
public class ReadProperties {

    private static final Logger LOG = LoggerFactory.getLogger(ReadProperties.class);
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader("src/test/resources/constant.properties"));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static synchronized String getProperty(String key) {
        return properties.getProperty(key);
    }
    public static synchronized int getIntProperty(String key){
        return Integer.parseInt(properties.getProperty(key));
    }
}

