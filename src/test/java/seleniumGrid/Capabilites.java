package seleniumGrid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilites {

    public static DesiredCapabilities setCapabilites() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.WINDOWS);
        return capability;
    }

}
