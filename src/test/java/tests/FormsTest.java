package tests;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
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
    @Step(value = "Запускает хромДрайвер и открывает ссылку на сайт")
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
    @Step(value = "Вводит параметры теста и проверяет на успешный вход")
    @Severity(SeverityLevel.BLOCKER)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с верными параметрами логина/пароля")
    @Test()
    public void enteringTrueValues() throws Exception {
        mainPage.enteringParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getPositiveResponseText(), ReadProperties.getProperty("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Step("Вводит параметры теста(неверный логин) и проверяет появление ошибки")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с НЕ верным параметром логина")
    @Test
    public void enteringFalseLogin() {
        mainPage.enteringParameters(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorMessageText(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Step("Вводит параметры теста(неверный пароль) и проверяет появление ошибки")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с НЕ верным параметром пароля")
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
    @Step("Вводит пустые логин/пароль/описание и проверяет появление ошибки")
    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с пустыми параметрами логина/пароля/описания")
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
    @Step(value = "Обновляет страницу")
    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().refresh();
    }

    /**
     * Закрывает браузер
     */
    @Step(value = "Закрывает хромДрайвер")
    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
