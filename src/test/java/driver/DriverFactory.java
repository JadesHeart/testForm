package driver;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.ReadProperties;

import java.io.IOException;
import java.net.URL;

import static grid.Capabilites.getCapabilites;
import static grid.StartBatScripts.startHub;
import static grid.StartBatScripts.startNode;
import static waits.Waiting.waitPositiveResponse;

public class DriverFactory {
    private static String pathToDriverDirectory = System.getProperty("user.dir") + "\\src\\test\\resources\\WebDrivers\\";

    public static WebDriver selectingDriver(Boolean remote, String browserName) throws IOException, InvalidResponseFromServer {
        if (remote) {
            try {
                return getGridDriver(browserName);
            } catch (Exception e) {
                startHub();
                waitPositiveResponse("http://localhost:4444/");
                startNode();
                waitPositiveResponse("http://localhost:5555/");
                return getGridDriver(browserName);
            }
        } else {
            switch (browserName) {
                case "Chrome":
                    return getChromeDriver();
                case "FireFox":
                    return getFireFoxDriver();
                case "IE":
                    return getIEDriver();
                case "Edge":
                    return getEdgeDriver();
                case "Opera":
                    return getOperaDriver();
                default:
                    return getChromeDriver();
            }
        }
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", pathToDriverDirectory + "chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver getFireFoxDriver() {
        System.setProperty("webdriver.gecko.driver", pathToDriverDirectory + "geckodriver.exe");
        return new FirefoxDriver();
    }

    private static WebDriver getIEDriver() {
        System.setProperty("webdriver.ie.driver", pathToDriverDirectory + "IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    private static WebDriver getOperaDriver() {
        System.setProperty("webdriver.opera.driver", pathToDriverDirectory + "operadriver.exe");
        return new OperaDriver();
    }

    private static WebDriver getEdgeDriver() {
        System.setProperty("webdriver.edge.driver", pathToDriverDirectory + "msedgedriver.exe");
        return new EdgeDriver();
    }

    private static WebDriver getGridDriver(String browserName) throws IOException {
        return new RemoteWebDriver(new URL(ReadProperties.getProperty("hub.url")), getCapabilites(browserName));
    }

}
