package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.ReadProperties;

import java.net.MalformedURLException;
import java.net.URL;

import static grid.Capabilites.getCapabilites;

public class GetDriver {
    public static WebDriver getDefaultDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webDriver\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver getGridDriver(String port) throws MalformedURLException {
        WebDriver driver;
        switch (port) {
            case "5555":
                return driver = new RemoteWebDriver(new URL(ReadProperties.getProperty("port5555")), getCapabilites());
            case "5556":
                return driver = new RemoteWebDriver(new URL(ReadProperties.getProperty("port5556")), getCapabilites());
            default:
                return null;
        }
    }
}
