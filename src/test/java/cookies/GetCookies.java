package cookies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetCookies {
    private static WebDriver driver;

    public GetCookies(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Берёт куки из файла, в который записали их при первой авторизации
     */
    public String getCookiesFromFile() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String sessionId = Buffreader.readLine();
            return sessionId;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}