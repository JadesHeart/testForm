package tests;

import org.openqa.selenium.WebDriver;
import getchromedriver.GetChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

import java.time.Duration;

import static propertiesConstants.PropertiesReader.getProperies;

/**
 * Класс с
 */
public class FormsTest {
    WebDriver driver = GetChromeDriver.getchromedriver();                   // Инициализация драйвера
    MainPage mainPage = new MainPage(driver);

    /**
     * Запуск браузера и открытие сайта
     */
    @BeforeTest
    public void startBrowser() throws Exception {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(getProperies("baseURL"));
    }

    /**
     * Ввожу верные параметры и получаю успешную авторизацию
     */
    @Test
    public void firstTestCase() throws Exception {
        mainPage.authorizationCorrectParameters();
        Assert.assertEquals(mainPage.checkingAuthorization(), getProperies("home"));
    }

    /**
     * Ввожу НЕ верные параметры(логин) и получаю ошибку
     */
    @Test
    public void secondTestCase() throws Exception {
        Assert.assertEquals(mainPage.authorizationINcorrectLogin(), getProperies("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Test
    public void thirdTestCase() throws Exception {
        Assert.assertEquals(mainPage.authorizationINcorrectPassword(), getProperies("UorPincorect"), "Ошибка не появилась");
    }

    /**
     * Поочерёдно пробует оставить поле пустым и получает ошибку.
     */
    @Test
    public void fourthTestCase() throws Exception {
        Assert.assertEquals(mainPage.authorizationWhithVoidParam(), getProperies("allWork"));
    }

    /**
     * Закрывает браузер
     */
    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
