package tests;

import cookies.AddCookies;
import cookies.GetCookies;
import cookies.WriteCookies;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AuthorizationPageSQLSite;
import properties.ReadProperties;

import java.io.IOException;
import java.time.Duration;

public class CookieAuthorizationTest {
    private static WebDriver driver;
    private static AuthorizationPageSQLSite dummyRegistration;
    private static WriteCookies writeCookies;
    private static GetCookies getCookies;
    private static AddCookies addCookies;

    /**
     * Инициализирует драйвер
     * Запускает браузер
     * Открывает сайт
     */
    @BeforeMethod
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webDriver\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        dummyRegistration = new AuthorizationPageSQLSite(driver);
        writeCookies = new WriteCookies(driver);
        getCookies = new GetCookies(driver);
        addCookies = new AddCookies(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ReadProperties.getProperty("DummyFormLink"));
    }

    /**
     * Берёт куки из файла.
     * Если есть то авторизирует через них и проверяет наличие непустого профиля
     * Если нет, то авторизируется как обычно и записывает куки в файл, так же проверяет наличие непустого профиля
     */
    @Test
    public void authorizationWithCookies() throws IOException {
        String sessionId = getCookies.getCookiesFromFile();
        if (sessionId != null) {
            AddCookies.addingCookies(sessionId);
            Assert.assertEquals(dummyRegistration.getProfileName(), ReadProperties.getProperty("profileName"));
        } else {
            dummyRegistration.enteringParameters(ReadProperties.getProperty("loginSQL"), ReadProperties.getProperty("passwordSQL"));
            dummyRegistration.clickLoginButton();
            WriteCookies.setCookiesInFile();
            Assert.assertEquals(dummyRegistration.getProfileName(), ReadProperties.getProperty("profileName"));
        }

    }

    /**
     * Закрывает драйвер
     */
    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
