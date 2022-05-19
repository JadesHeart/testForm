package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class TabsPage {
    private WebDriver driver;

    @FindBy(linkText = "New Browser Tab")
    private WebElement newBrowserTabButton;
    @FindBy(css = "#example-1-tab-1  iframe")
    private WebElement frameWindow;

    public TabsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Указывает на работу с окном фрэйма")
    public void windowEntry() {
        waitElementDisplays(frameWindow, driver);
        driver.switchTo().frame(frameWindow);
    }

    @Step("Кликает на кнопку нового окна")
    public void clickOnNewBrowserTabButton() {
        waitElementDisplays(newBrowserTabButton, driver);
        newBrowserTabButton.click();
    }
}
