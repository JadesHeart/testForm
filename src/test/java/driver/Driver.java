package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.ReadProperties;

import java.io.IOException;
import java.net.URL;

import static grid.Capabilites.getCapabilites;

public class Driver {
    private static WebDriver getChromeDriver() {
        return new ChromeDriver();
    }

    private static WebDriver getWebGridDriver() throws IOException {
        return new RemoteWebDriver(new URL(ReadProperties.getProperty("hub.url")), getCapabilites());
    }

    public static WebDriver selectingRemoteDriver(Boolean remote) throws IOException {
        if (remote) {
            return getWebGridDriver();
        } else {
            return getChromeDriver();
        }
    }
}
