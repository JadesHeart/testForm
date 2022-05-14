package tests;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import properties.ReadProperties;

import java.io.IOException;

import static driver.DriverFactory.selectingDriver;

public class BaseTestClass {
    private WebDriver driver;

    @BeforeTest
    public void startDriver() throws InvalidResponseFromServer, IOException {
        driver = selectingDriver(ReadProperties.getBoolProperty("remote"), ReadProperties.getProperty("browser"));
    }

    public WebDriver getBaseDriver() {
        return driver;
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}