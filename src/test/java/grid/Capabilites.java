package grid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilites {

    public static DesiredCapabilities getCapabilites() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setPlatform(Platform.WINDOWS);
        return capability;
    }

}
