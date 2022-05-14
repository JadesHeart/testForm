package tests;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import properties.ReadProperties;

import java.io.IOException;

import static driver.DriverFactory.selectingDriver;

public class BaseTestClass {
    private static WebDriver driver;

    public static WebDriver startDriver() throws InvalidResponseFromServer, IOException {
        driver = selectingDriver(ReadProperties.getBoolProperty("remote"), ReadProperties.getProperty("browser"));
        return driver;
    }

    public void closeDriver(WebDriver driver) {
        driver.quit();
    }
}