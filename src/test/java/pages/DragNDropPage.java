package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class DragNDropPage {

    private WebDriver driver;

    @FindBy(id = "draggable")
    private WebElement draggableElement;
    @FindBy(id = "droppable")
    private WebElement draggedElement;
    @FindBy(css = "#example-1-tab-1  iframe")
    private WebElement transferWindow;

    public DragNDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Указывает на работу с окном DragNDrop'a")
    public void windowEntry() {
        waitElementDisplays(transferWindow, driver);
        driver.switchTo().frame(transferWindow);
    }

    @Step("Совершает DragNDrop")
    public void dragNDrop(Actions actions) {
        waitElementDisplays(draggableElement, driver);
        actions.dragAndDrop(draggableElement, draggedElement).build().perform();
    }

    @Step("Возвращает текст с элемента, после переноса")
    public String getDraggedElementText() {
        return draggedElement.getText();
    }
}
