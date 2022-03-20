package properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * класс проперти
 */
public class PropertiesReader {

    public static Properties getProperies() throws IOException {
        Properties properties = new Properties(); // создаёт новый проперти
        return properties; // возвращает значение по ключу propertiKey
    }
    public static FileReader getPathProperties() throws IOException{
        FileReader reader = new FileReader("src/test/java/constant.properties"); // путь к файлу с пропертями
        return reader;
    }
}
