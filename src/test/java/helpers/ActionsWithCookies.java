package helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ActionsWithCookies {
    private WebDriver driver;

    public ActionsWithCookies(WebDriver driver) {
        this.driver = driver;
    }

    public void addingCookies(String sessionId) {
        Cookie cookie = new Cookie("PHPSESSID", sessionId);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    /**
     * Берёт куки из файла, в который записали их при первой авторизации
     */
    public String returnSessionID() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (BufferedReader Buffreader = new BufferedReader(new FileReader(file))) {
            return Buffreader.readLine();
        }
    }

    public void saveCookies() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (FileWriter fileWrite = new FileWriter(file)) {
            file.delete();
            file.createNewFile();
            fileWrite.write(driver.manage().getCookieNamed("PHPSESSID").getValue());
        }
    }
}
