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

    public static WebDriver getGridDriver() throws MalformedURLException {
        WebDriver driver;
        return driver = new RemoteWebDriver(new URL(ReadProperties.getProperty("port5555")), getCapabilites());
    }

    public static WebDriver getChromeDriver(String typeOfDriver) throws MalformedURLException {
        if (typeOfDriver == "Default") {
            return getDefaultDriver();
        } else if (typeOfDriver == "Grid") {
            return getGridDriver();
        } else {
            return null;
        }
    }
}
