package tests;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import properties.ReadProperties;

import java.io.IOException;

import static driver.DriverFactory.selectingDriver;

public class BaseTestClass {
    protected WebDriver driver;

    @BeforeTest
    public void startDriver() throws InvalidResponseFromServer, IOException {
        driver = selectingDriver(ReadProperties.getBoolProperty("remote"), ReadProperties.getProperty("browser"));
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}