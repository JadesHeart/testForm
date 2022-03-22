package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import webdriver.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

import java.lang.reflect.Method;
import java.time.Duration;

import ReadProperties.ReadProperties;

/**
 * Класс с
 */
public class FormsTest {
    WebDriver driver = ChromeDriver.getChromeDriver();
    MainPage mainPage = new MainPage(driver);

    /**
     * Запуск браузера и открытие сайта
     */
    @BeforeTest
    public void startBrowser() throws Exception {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ReadProperties.getProperty("baseURL"));
    }

    /**
     * Ввожу верные параметры и получаю успешную авторизацию
     */
    @Test
    public void enteringTrueValues() throws Exception {
        mainPage.authorizationCorrectParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        Assert.assertEquals(mainPage.getAuthorizationMessage(), ReadProperties.getProperty("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Test
    public void enteringFalseLogin() throws Exception {
        Assert.assertEquals(mainPage.authorizationINcorrectLoginOrPassword(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description")), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Test
    public void enteringFalsePassword() throws Exception {
        Assert.assertEquals(mainPage.authorizationINcorrectLoginOrPassword(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description")), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    @DataProvider(name = "EnterVoidValuesDataProvide")
    public Object[][] dpMethodTwo(Method m) {
        switch (m.getName()) {
            case "voidloginOrPassword":
                return new Object[][]{{"", "SomeText", "SomeText", "//*[@class=\"help-block ng-active\"]/descendant::div"},
                        {"SomeText", "", "SomeText", "//*[@class=\"help-block ng-active\"]/descendant::div"}};
            case "voidDesc":
                return new Object[][]{{"SomeText", "SomeText", "", "//*[contains(@class,\" ng-scope has-error\")]"}};
        }
        return null;
    }

    /**
     * Оставляю поочерёдно поля login/password пустми и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Test(dataProvider = "EnterVoidValuesDataProvide")
    public void voidloginOrPassword(String lg, String ps, String descr, String path) throws Exception {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam(lg, ps, descr, path), ReadProperties.getProperty("allWork"));
        driver.navigate().refresh();
    }

    /**
     * Оставляю поочерёдно поле username description пустм и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Test(dataProvider = "EnterVoidValuesDataProvide")
    public void voidDesc(String lg, String ps, String descr, String path) throws Exception {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam(lg, ps, descr, path), ReadProperties.getProperty("allWork1"));
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
