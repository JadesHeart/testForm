package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.TabsPage;
import properties.ReadProperties;
import tabs.SwitcherWindows;

public class TabsTest extends BaseTestClass {
    private static TabsPage tabsPage;
    private static SwitcherWindows switcherWindows;
    private static int quantityTabs = 1;

    @BeforeTest
    public void startBrowser() {
        tabsPage = new TabsPage(driver);
        switcherWindows = new SwitcherWindows(driver);
        driver.get(ReadProperties.getProperty("browserTabURL"));
    }

    @Test
    public void testOpeningSecondTab() {
        tabsPage.windowEntry();
        tabsPage.clickOnNewBrowserTabButton();
        Assert.assertEquals(switcherWindows.getQuantityTabs(), quantityTabs + 1, "Вторая вкладка не открылась");
        quantityTabs = switcherWindows.increaseTabCounter(quantityTabs);
    }

    @Test
    public void testOpeningThirdTab() {
        tabsPage.clickOnNewBrowserTabButton();
        Assert.assertEquals(switcherWindows.getQuantityTabs(), quantityTabs + 1, "Третья вкладка не открылась");
    }
}
