package tests;

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

    @Test
    public void testDragNDrop() {
        actions = new Actions(driver);
        dragNDropPage.windowEntry();
        dragNDropPage.dragNDrop(actions);
        Assert.assertEquals(dragNDropPage.getDraggedElementText(), "Dropped!", "Элемент не перенесённ");
    }
}
