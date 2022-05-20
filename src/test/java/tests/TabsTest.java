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

import static helpers.SwitcherWindows.numbersTabs;

public class TabsTest extends BaseTestClass {
    private static TabsPage tabsPage;

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
        Assert.assertEquals(numbersTabs(driver), 2, "Вторая вкладка не открылась");
        tabsPage.clickOnNewBrowserTabButton();
        Assert.assertEquals(numbersTabs(driver), 3, "Третья вкладка не открылась");
    }
}
