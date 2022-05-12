package grid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilites {

    public static DesiredCapabilities getCapabilites(String browserName) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(browserName);
        capability.setPlatform(Platform.WINDOWS);
        return capability;
    }

}
