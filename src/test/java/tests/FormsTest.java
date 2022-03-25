package tests;

import ReadProperties.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\лали\\IdeaProjects\\testAngularjsProtractor\\webDriver\\chromedriver\\chromedriver.exe");
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
        Assert.assertEquals(mainPage.authorizationCorrectParameters(), ReadProperties.getProperty("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Test
    public void enteringFalseLogin() {
        mainPage.enteringParameters(ReadProperties.getProperty("falseLogin"), ReadProperties.getProperty("password"), ReadProperties.getProperty("description"));
        Assert.assertEquals(mainPage.authorizationINcorrectLoginOrPassword(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Test
    public void enteringFalsePassword() {
        mainPage.enteringParameters(ReadProperties.getProperty("login"), ReadProperties.getProperty("falsePassword"), ReadProperties.getProperty("description"));
        Assert.assertEquals(mainPage.authorizationINcorrectLoginOrPassword(), ReadProperties.getProperty("UorPincorect"), "Ошибка не появилась");
    }

    @DataProvider(name = "EnterVoidValuesDataProvide")
    public Object[][] dpMethodTwo(Method m) {
        return new Object[][]{{"", "SomeText", "SomeText", "//*[@class=\"help-block ng-active\"]/descendant::div"},
                {"SomeText", "", "SomeText", "//*[@class=\"help-block ng-active\"]/descendant::div"}};
    }

    /**
     * Оставляю поочерёдно поля login/password пустми и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Test(dataProvider = "EnterVoidValuesDataProvide")
    public void voidloginOrPassword(String lg, String ps, String descr, String path) {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam(lg, ps, descr, path), ReadProperties.getProperty("allWork"));
        driver.navigate().refresh();
    }

    /**
     * Оставляю поочерёдно поле username description пустм и проверяю, что кнопка Login не работает и появляется ошибка
     */
    @Test(dataProvider = "EnterVoidValuesDataProvide")
    public void voidDesc() {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam("SomeText", "SomeText", "", "//*[contains(@class,\" ng-scope has-error\")]"), ReadProperties.getProperty("allWork1"));
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
