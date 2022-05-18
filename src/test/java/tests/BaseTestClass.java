package tests;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import properties.ReadProperties;

import java.io.IOException;
import java.time.Duration;

import static driver.DriverFactory.selectingDriver;

abstract public class BaseTestClass {
    protected WebDriver driver;

    @BeforeTest
    public void startDriver() throws InvalidResponseFromServer, IOException {
        driver = selectingDriver(ReadProperties.getBoolProperty("remote"), ReadProperties.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}