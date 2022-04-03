package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DummyRegistration;
import properties.ReadProperties;

import java.io.IOException;
import java.time.Duration;

public class DummyFormTest {
    private static WebDriver driver;
    private static DummyRegistration dummyRegistration;

    /**
     * Инициализирует драйвер
     * Запускает браузер
     * Открывает сайт
     */
    @BeforeMethod
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webDriver\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        dummyRegistration = new DummyRegistration(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ReadProperties.getProperty("DummyFormLink"));
    }

    /**
     * Проверяет, что в файл записались верные куки с сайта.
     */
    @Test(priority = 1)
    public void wrightCookies() throws IOException {
        dummyRegistration.enteringParameters(ReadProperties.getProperty("loginSQL"), ReadProperties.getProperty("passwordSQL"));
        dummyRegistration.clickLoginButton();
        dummyRegistration.setCookiesInFile();
        Assert.assertEquals(dummyRegistration.getCookiesFromSite(), dummyRegistration.getCookiesFromFile());
    }

    /**
     * Запускает сайт с куками из файла и проверяет, что они применились.
     */
    @Test(priority = 2)
    public void authorizationWithCookies() throws IOException {
        String sessionId = dummyRegistration.getCookiesFromFile();
        dummyRegistration.addingCookies(sessionId);
        Assert.assertEquals(dummyRegistration.getCookiesFromSite(), sessionId);
    }

    /**
     * Закрывает драйвер
     */
    @AfterMethod
    public void driverClose(){
        driver.quit();
    }
    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
