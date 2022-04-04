package cookies;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ActionsWithCookies {
    private static WebDriver driver;

    public ActionsWithCookies(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void addingCookies(String sessionId) {
        Cookie cookie = new Cookie("PHPSESSID", sessionId);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    /**
     * Берёт куки из файла, в который записали их при первой авторизации
     */
    public static String returnSessionID() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (FileReader fileReader = new FileReader(file);) {
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String sessionId = Buffreader.readLine();
            return sessionId;
        }
    }

    public static void setCookiesInFile() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (FileWriter fileWrite = new FileWriter(file);
             BufferedWriter Bwrite = new BufferedWriter(fileWrite)) {
            file.delete();
            file.createNewFile();
            Bwrite.write(driver.manage().getCookieNamed("PHPSESSID").getValue());
            Bwrite.newLine();
        }
    }
}
