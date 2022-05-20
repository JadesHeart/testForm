package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.TabsPage;
import properties.ReadProperties;

import static tabs.SwitcherWindows.getQuantityTabs;

public class TabsTest extends BaseTestClass {
    private static TabsPage tabsPage;
    private static int quantityTabs = 1;

    @BeforeTest
    public void startBrowser() {
        tabsPage = new TabsPage(driver);
        driver.get(ReadProperties.getProperty("browserTabURL"));
    }

    @Description(value = "Тест проверяет что новая вкладка открылась")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тестирование формы для открытия новой вкладки")
    @Story(value = "Открытие новой вкладки")
    @Test
    public void testOpeningTabs() {
        tabsPage.windowEntry();
        tabsPage.clickOnNewBrowserTabButton();
        Assert.assertEquals(getQuantityTabs(driver), quantityTabs + 1, "Вторая вкладка не открылась");
        ++quantityTabs;
        tabsPage.clickOnNewBrowserTabButton();
        Assert.assertEquals(getQuantityTabs(driver), quantityTabs + 1, "Третья вкладка не открылась");
    }
}
