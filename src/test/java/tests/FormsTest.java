package tests;

import grid.InvalidResponseFromServer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listener.FailureListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;
import properties.ReadProperties;
import scripts.JavaScriptMethods;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;


/**
 * Класс с
 */
@Listeners(FailureListener.class)
//@Test(priority = 2, retryAnalyzer = StartFailedTests.class)
public class FormsTest extends BaseTestClass {
    private static MainPage mainPage;
    private static JavaScriptMethods javaScripts;

    /**
     * Запуск браузера и открытие сайта
     */
    @BeforeTest
    public void startBrowser() throws InvalidResponseFromServer, IOException {
        mainPage = new MainPage(driver);
        javaScripts = new JavaScriptMethods(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ReadProperties.getProperty("baseURL"));
    }

    @Description(value = "Тест ищит элемент с неверным css-селектором и не найдя падает")
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест страницы авторизации")
    @Story(value = "Проверка наличия скрола страницы")
    @Test
    public void testJavaScriptExecutor() {
        mainPage.enteringParameters(ReadProperties.getProperty("profile.login"), ReadProperties.getProperty("profile.password"), ReadProperties.getProperty("profile.description"));
        javaScripts.removeCursor(mainPage.getDescription());
        Assert.assertFalse(javaScripts.checkScroll(mainPage.getBody()), "Высота скролла больше заданной высоты");
    }

    @Description(value = "Тест ищит элемент с неверным css-селектором и не найдя падает")
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Падающие тесты")
    @Story(value = "Попытка получить текст из несуществующего элемента")
    @Test(enabled = false)
    public void waitingMissingElement() {
        Assert.assertEquals(mainPage.getTextFromNonExistentElement(), ReadProperties.getProperty("EmptyText"));
    }

    @Description(value = "Тест берёт текст из элемента и ассёртит с случайным текстом и получает ошибку")
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Падающие тесты")
    @Story(value = "Текст из элемента сравнивается с другим текстом")
    @Test(priority = 2, enabled = false)
    public void testHeadlineUserNameText() {
        Assert.assertEquals(mainPage.getTextFromHeadlineUserName(), ReadProperties.getProperty("EmptyText"));
    }


    /**
     * Ввожу верные параметры и получаю успешную авторизацию
     */
    @Description(value = "Тест проверяет успешную авторизацию при верных параметрах логина и пароля")
    @Severity(SeverityLevel.BLOCKER)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с верными параметрами логина/пароля")
    @Test
    public void enteringTrueValues() {
        mainPage.enteringParameters(ReadProperties.getProperty("profile.login"), ReadProperties.getProperty("profile.password"), ReadProperties.getProperty("profile.description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getPositiveResponseText(), ReadProperties.getProperty("home"));
        driver.get(ReadProperties.getProperty("baseURL"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Description(value = "Тест проверяет наличие ошбки при попытке авторизации с неверным логином")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с НЕ верным параметром логина")
    @Test
    public void enteringFalseLogin() {
        mainPage.enteringParameters(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("profile.password"), ReadProperties.getProperty("profile.description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorMessageText(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    @Description(value = "Тест проверяет наличие ошбки при попытке авторизации с неверным паролем")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с НЕ верным параметром пароля")
    @Test
    public void enteringFalsePassword() {
        mainPage.enteringParameters(ReadProperties.getProperty("profile.login"), ReadProperties.getProperty("falsePassword"), ReadProperties.getProperty("profile.description"));
        mainPage.clickLoginButton();
        Assert.assertEquals(mainPage.getErrorMessageText(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    @DataProvider(name = "inputEmptyParameters")
    public Object[][] dpMethodTwo(Method m) {
        return new Object[][]{{"", "SomeText", "SomeText"},
                {"SomeText", "", "SomeText"},
                {"SomeText", "SomeText", ""}};
    }

    /**
     * Оставляю поочерёдно поля login/password пустми и проверяю, что кнопка Login не работает и появляется ошибка
     */

    @Severity(SeverityLevel.NORMAL)
    @Description(value = "Тест проверяет наличие ошбки при попытке авторизации с пустым(и) параметром(и)")
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тест формы авторизации")
    @Story(value = "Авторизация с пустыми параметрами логина/пароля/описания")
    @Test(dataProvider = "inputEmptyParameters")
    public void inputEmptyParameters(String login, String password, String description) {
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
}
