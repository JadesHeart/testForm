package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasicAuthPage;
import properties.ReadProperties;

public class BasicAuthTest extends BaseTestClass {
    private BasicAuthPage basicAuthPage;

    @BeforeTest
    public void startBrowser() {
        basicAuthPage = new BasicAuthPage(driver);
        String link = "https://" + ReadProperties.getProperty("basicauth.login") + ":" + ReadProperties.getProperty("basicauth.password") + "@" + ReadProperties.getProperty("url.basicauth");
        driver.get(link);
    }

    @Description(value = "Тест проверяет на успешную базовую  аутентификация")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с помощью базовой аутентификации")
    @Test
    public void testBasicAuth() {
        basicAuthPage.clickOnDisplayImage();
        Assert.assertTrue(basicAuthPage.getDisplayImageStatus(), ReadProperties.getProperty("basicAuthMessage"));
    }
}
