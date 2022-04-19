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

    public static WebDriver getGridDriver(Boolean startHub) throws IOException, InvalidResponseFromServer {
        WebDriver driver;
        if (startHub) {
            startHub();
            waitPositiveResponse("http://localhost:4444/");
        }
        startFirstNode();
        waitPositiveResponse("http://localhost:5555/");
        return driver = new RemoteWebDriver(new URL(ReadProperties.getProperty("port5555")), getCapabilites());
    }

    public static WebDriver getChromeDriver(String typeOfDriver, Boolean startHub) throws IOException, InvalidResponseFromServer {
        if (typeOfDriver == "Default") {
            return getDefaultDriver();
        } else if (typeOfDriver == ReadProperties.getProperty("grid")) {
            return getGridDriver(startHub);
        } else {
            return null;
        }
    }
}
