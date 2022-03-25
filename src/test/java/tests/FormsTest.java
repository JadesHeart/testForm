package tests;

import properties.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import pages.MainPage;

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
    @Test
    public void enteringTrueValues() throws Exception {
        mainPage.enteringParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getPositiveResponseText(), ReadProperties.getProperty("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Test
    public void enteringFalseLogin() {
        mainPage.enteringParameters(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorTextFromFalseLogOrPas(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Test
    public void enteringFalsePassword() {
        mainPage.enteringParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("falsePassword"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorTextFromFalseLogOrPas(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
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
    @Test(dataProvider = "inputVoidParameters")
    public void inputVoidParameters(String login, String password, String description) {
        if (description == "") {
            mainPage.enteringParameters(login, password, description);
            mainPage.clickPassword();
            if (mainPage.loginButtonIsUnwork()) {
                Assert.assertEquals(mainPage.getErrorTextFromVoidDescription(), ReadProperties.getProperty("allWork1"));
            }
        } else if (login == "") {
            mainPage.enteringParameters(login, password, description);
            if (mainPage.loginButtonIsUnwork()) {
                Assert.assertEquals(mainPage.getErrorTextFromVoidLogin(), ReadProperties.getProperty("allWork"));
            }
        } else if (password == "") {
            if (mainPage.loginButtonIsUnwork()) {
                mainPage.enteringParameters(login, password, description);
                Assert.assertEquals(mainPage.getErrorTextFromVoidPassword(), ReadProperties.getProperty("allWork"));
            }
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
