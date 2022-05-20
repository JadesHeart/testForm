package tabs;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SwitcherWindows {
    public static int getQuantityTabs(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }
}
