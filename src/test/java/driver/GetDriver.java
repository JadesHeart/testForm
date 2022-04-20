package driver;

import grid.InvalidResponseFromServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.ReadProperties;

import java.io.IOException;
import java.net.URL;

import static grid.Capabilites.getCapabilites;
import static grid.StartBatScripts.startFirstNode;
import static grid.StartBatScripts.startHub;
import static waits.Waiting.waitPositiveResponse;

public class GetDriver {
    public static WebDriver getDefaultDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webDriver\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver getGridDriver() throws IOException, InvalidResponseFromServer {
        waitPositiveResponse("http://localhost:4444/");
        waitPositiveResponse("http://localhost:5555/");
        return new RemoteWebDriver(new URL(ReadProperties.getProperty("port5555")), getCapabilites());
    }

    public static WebDriver getChromeDriver(String driverType) throws IOException, InvalidResponseFromServer {
        if (driverType == "Default") {
            return getDefaultDriver();
        } else if (driverType == ReadProperties.getProperty("grid")) {
            startHub();
            waitPositiveResponse("http://localhost:4444/");
            startFirstNode();
            waitPositiveResponse("http://localhost:5555/");
            return getGridDriver();
        } else {
            return null;
        }
    }
}
