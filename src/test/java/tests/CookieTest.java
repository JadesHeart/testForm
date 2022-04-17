package tests;

import cookies.ActionsWithCookies;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SQLExPage;
import properties.ReadProperties;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import static seleniumGrid.Capabilites.setCapabilites;
import static seleniumGrid.StartBatScripts.startFirstNode;
import static seleniumGrid.StartBatScripts.startHub;

public class CookieTest {
    private WebDriver driver;
    private SQLExPage dummyRegistration;
    private ActionsWithCookies addCookies;

    /**
     * Инициализирует драйвер
     * Запускает браузер
     * Открывает сайт
     */
    @BeforeMethod
    public void startBrowser() throws IOException, InterruptedException {
        startHub();
        Thread.sleep(5000);
        startFirstNode();
        Thread.sleep(5000);

        driver = new RemoteWebDriver(new URL("http://192.168.0.11:5555/wd/hub"), setCapabilites());

        dummyRegistration = new SQLExPage(driver);
        addCookies = new ActionsWithCookies(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ReadProperties.getProperty("DummyFormLink"));
    }

    /**
     * Берёт куки из файла.
     * Если есть то авторизирует через них и проверяет наличие непустого профиля
     * Если нет, то авторизируется как обычно и записывает куки в файл, так же проверяет наличие непустого профиля
     */
    @Description(value = "Тест проверяет на успешную авторизацию при наличии куков")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с помощью куков")
    @Test
    public void authorizationWithCookies() throws IOException {
        String sessionId = addCookies.returnSessionID();
        if (sessionId != null) {
            addCookies.addingCookies(sessionId);
        } else {
            dummyRegistration.enteringParameters(ReadProperties.getProperty("loginSQL"), ReadProperties.getProperty("passwordSQL"));
            dummyRegistration.clickLoginButton();
            addCookies.saveCookies();
        }
        Assert.assertEquals(dummyRegistration.getProfileName(), ReadProperties.getProperty("profileName"));
    }

    /**
     * Закрывает драйвер
     */
    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
