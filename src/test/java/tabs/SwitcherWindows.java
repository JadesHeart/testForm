package tabs;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SwitcherWindows {
    private WebDriver driver;

    public SwitcherWindows(WebDriver driver) {
        this.driver = driver;
    }

    public int getQuantityTabs() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }

    public int increaseTabCounter(int number) {
        return ++number;
    }
}
