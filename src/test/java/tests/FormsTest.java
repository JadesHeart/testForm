package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;
import properties.ReadProperties;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * Класс с
 */
public class FormsTest {
    private static WebDriver driver;
    private static MainPage mainPage;

    /**
     * Запуск браузера и открытие сайта
     */

    @BeforeTest
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webDriver\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ReadProperties.getProperty("baseURL"));
    }

    /**
     * Ввожу верные параметры и получаю успешную авторизацию
     */
    @Severity(SeverityLevel.BLOCKER)
    @Epic(value = "Ввод верных значений.")
    @Test
    public void enteringTrueValues() throws Exception {
        mainPage.enteringParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getPositiveResponseText(), ReadProperties.getProperty("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Ввод НЕ верных значений.")
    @Feature(value = "Ввод несуществующих значений.")
    @Story(value = "Ввод неверного логина")
    @Test
    public void enteringFalseLogin() {
        mainPage.enteringParameters(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorMessageText(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Ввод НЕ верных значений.")
    @Feature(value = "Ввод несуществующих значений.")
    @Story(value = "Ввод неверного пароля")
    @Test
    public void enteringFalsePassword() {
        mainPage.enteringParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("falsePassword"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorMessageText(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    @DataProvider(name = "inputVoidParameters")
    public Object[][] dpMethodTwo(Method m) {
        return new Object[][]{{"", "SomeText", "SomeText"},
                {"SomeText", "", "SomeText"},
                {"SomeText", "SomeText", ""}};
    }

    /**
     * Оставляю поочерёдно поля login/password пустми и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Ввод НЕ верных значений.")
    @Feature(value = "Ввод пустых значений в поля.")
    @Test(dataProvider = "inputVoidParameters")
    public void inputVoidParameters(String login, String password, String description) {
        mainPage.enteringParameters(login, password, description);
        Assert.assertFalse(mainPage.isLoginButtonEnabled());
        if (description == "") {
            mainPage.clickPassword();
            Assert.assertEquals(mainPage.getErrorTextFromEmptyDescription(), ReadProperties.getProperty("allWork1"));
        }
        if (login == "") {
            Assert.assertEquals(mainPage.getErrorTextFromEmptyLogin(), ReadProperties.getProperty("allWork"));
        }
        if (password == "") {
            Assert.assertEquals(mainPage.getErrorTextFromEmptyPassword(), ReadProperties.getProperty("allWork"));
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().refresh();
    }

    /**
     * Закрывает браузер
     */
    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
