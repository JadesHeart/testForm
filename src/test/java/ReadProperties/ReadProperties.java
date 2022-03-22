package ReadProperties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * класс проперти
 */
public class ReadProperties {

    private static final Logger LOG = LoggerFactory.getLogger(ReadProperties.class);
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader("src/test/java/constant.properties"));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static synchronized String getProperty(String key) {
        return properties.getProperty(key);
    }
}

