package tabs;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SwitcherWindows {
    public static int getQuantityTabs(WebDriver driver) {
        return new ArrayList<>(driver.getWindowHandles()).size();
    }
}
