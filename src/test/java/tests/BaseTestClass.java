package tests;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import properties.ReadProperties;

import java.io.IOException;

import static driver.DriverFactory.selectingDriver;

public class BaseTestClass {
    private static WebDriver driver;

    @BeforeSuite
    public void startGrid() throws InvalidResponseFromServer, IOException {
        driver = selectingDriver(ReadProperties.getBoolProperty("remote"), ReadProperties.getProperty("browser"));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }
}