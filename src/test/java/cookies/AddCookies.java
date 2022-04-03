package cookies;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AddCookies {
    private static WebDriver driver;

    public AddCookies(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void addingCookies(String sessionId) {
        Cookie cookie = new Cookie("PHPSESSID", sessionId);
        try {
            driver.manage().addCookie(cookie);
            driver.navigate().refresh();
        } catch (
                Exception ex) {
            ex.printStackTrace();

        }
    }
}
