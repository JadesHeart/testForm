package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import properties.PropertiesReader;
import webdriver.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import static properties.PropertiesReader.getPathProperties;

/**
 * Класс с
 */
public class FormsTest {
    WebDriver driver = ChromeDriver.getChromeDriver();
    MainPage mainPage = new MainPage(driver);
    Properties properties = PropertiesReader.getProperies();


    public FormsTest() throws IOException {
        properties.load(getPathProperties());
    }

    /**
     * Запуск браузера и открытие сайта
     */
    @BeforeTest
    public void startBrowser() throws Exception {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("baseURL"));
    }

    @DataProvider(name = "EnteringValues")
    public Object[][] dpMethodOne(Method m) throws IOException {
        switch (m.getName()) {
            case "enteringFalseLogin":
                return new Object[][]{{properties.getProperty("falseLogin"), properties.getProperty("password"), properties.getProperty("description")}};
            case "enteringFalsePassword":
                return new Object[][]{{properties.getProperty("login"), properties.getProperty("falsePassword"), properties.getProperty("description")}};
        }
        return null;
    }

    /**
     * Ввожу верные параметры и получаю успешную авторизацию
     */
    @Test
    public void enteringTrueValues() throws Exception {
        mainPage.authorizationCorrectParameters(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("description"));
        Assert.assertEquals(mainPage.getAuthorizationMessage(), properties.getProperty("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Test(dataProvider = "EnteringValues")
    public void enteringFalseLogin(String lg, String ps, String descr) throws Exception {
        Assert.assertEquals(mainPage.authorizationINcorrectLoginOrPassword(lg, ps, descr), properties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Test(dataProvider = "EnteringValues")
    public void enteringFalsePassword(String lg, String ps, String descr) throws Exception {
        Assert.assertEquals(mainPage.authorizationINcorrectLoginOrPassword(lg, ps, descr), properties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    @DataProvider(name = "EnterVoidValuesDataProvide")
    public Object[][] dpMethodTwo(Method m) {
        switch (m.getName()) {
            case "VoidloginOrPassword":
                return new Object[][]{{"", "SomeText", "SomeText", "//*[@class=\"help-block ng-active\"]/descendant::div"},
                        {"SomeText", "", "SomeText", "//*[@class=\"help-block ng-active\"]/descendant::div"}};
            case "VoidDesc":
                return new Object[][]{{"SomeText", "SomeText", "", "//*[contains(@class,\" ng-scope has-error\")]"}};
        }
        return null;
    }

    /**
     * Оставляю поочерёдно поля login/password пустми и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Test(dataProvider = "EnterVoidValuesDataProvide")
    public void VoidloginOrPassword(String lg, String ps, String descr, String path) throws Exception {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam(lg, ps, descr, path), properties.getProperty("allWork"));
        driver.navigate().refresh();
    }

    /**
     * Оставляю поочерёдно поле username description пустм и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Test(dataProvider = "EnterVoidValuesDataProvide")
    public void VoidDesc(String lg, String ps, String descr, String path) throws Exception {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam(lg, ps, descr, path), properties.getProperty("allWork1"));
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
