package tests;

import helpers.ActionsWithCookies;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SQLExPage;
import properties.ReadProperties;

import java.io.IOException;

public class CookieTest extends BaseTestClass {
    private SQLExPage dummyRegistration;
    private ActionsWithCookies addCookies;

    /**
     * Инициализирует драйвер
     * Запускает браузер
     * Открывает сайт
     */
    @BeforeTest
    public void startBrowser() {
        dummyRegistration = new SQLExPage(driver);
        addCookies = new ActionsWithCookies(driver);
        driver.get(ReadProperties.getProperty("url.dummyform"));
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
}
