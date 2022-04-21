package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.ReadProperties;

import java.io.IOException;
import java.net.URL;

import static grid.Capabilites.getCapabilites;

public class GetDriver {
    public static WebDriver getDefaultDriver() {
        return new ChromeDriver();
    }

    public static WebDriver getGridDriver() throws IOException {
        return new RemoteWebDriver(new URL(ReadProperties.getProperty("nodePort")), getCapabilites());
    }

    public static WebDriver getChromeDriver(String driverType) throws IOException {
        if (driverType == "Default") {
            return getDefaultDriver();
        } else if (driverType == ReadProperties.getProperty("grid")) {
            return getGridDriver();
        } else {
            return null;
        }
    }
}
