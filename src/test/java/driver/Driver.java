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

    private static Boolean remoteBoolean(String remote) {
        if (remote == "Boolean.TRUE") {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static WebDriver selectingRemoteDriver(String remote) throws IOException {
        if (remoteBoolean(remote)) {
            return getWebGridDriver();
        } else {
            return getChromeDriver();
        }
    }
}
