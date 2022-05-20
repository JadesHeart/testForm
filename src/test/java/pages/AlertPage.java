package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class AlertPage {
    private WebDriver driver;

    @FindBy(css = ".internal_navi :nth-child(2) > a")
    private WebElement inputAlertButton;
    @FindBy(css = "body > button")
    private WebElement buttonDisplayedAlert;
    @FindBy(css = "#example-1-tab-2 > div > iframe")
    private WebElement frameWindow;
    @FindBy(id = "demo")
    private WebElement alertMessage;


    public AlertPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Указывает на работу с окном фрэйма")
    public void windowEntry() {
        waitElementDisplays(frameWindow, driver);
        driver.switchTo().frame(frameWindow);
    }

    @Step("Кликает на кнопку Input Alert")
    public void clickInputAlertButtonButton() {
        waitElementDisplays(inputAlertButton, driver);
        inputAlertButton.click();
    }

    @Step("Кликает на кнопку Displayed Alert")
    public void clickDisplayedAlertButton() {
        waitElementDisplays(buttonDisplayedAlert, driver);
        buttonDisplayedAlert.click();
    }

    @Step("Вовзращает мэсендж алерта из блока на странице")
    public String getAlertsMessage() {
        return alertMessage.getText();
    }
}
