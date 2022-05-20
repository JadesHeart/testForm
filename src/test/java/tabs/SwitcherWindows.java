package tabs;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SwitcherWindows {
    public static int numbersTabs(WebDriver driver) {
        return new ArrayList<>(driver.getWindowHandles()).size();
    }
}
