package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DragNDropPage;
import properties.ReadProperties;

public class DragNDropTest extends BaseTestClass {
    private static DragNDropPage dragNDropPage;
    private static Actions actions;

    @BeforeTest
    public void startBrowser() {
        dragNDropPage = new DragNDropPage(driver);
        driver.get(ReadProperties.getProperty("dragNDropURL"));
    }

    @Description(value = "Тест проверяет dragNDrop элемента")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тестирование формы DragNDropa")
    @Story(value = "DragNDrop элементов")
    @Test
    public void testDragNDrop() {
        actions = new Actions(driver);
        dragNDropPage.windowEntry();
        dragNDropPage.dragNDrop(actions);
        Assert.assertEquals(dragNDropPage.getDraggedElementText(), "Dropped!", "Элемент не перенесённ");
    }
}
