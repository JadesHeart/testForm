package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class BasicAuthPage {
    private WebDriver driver;

    @FindBy(id = "displayImage")
    private WebElement displayImageButton;
    @FindBy(id = "downloadImg")
    private WebElement authenticatedImage;


    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Кликает на кнопку Display Image")
    public void clickOnDisplayImage() {
        displayImageButton.click();
    }

    @Step("Проверяет отображается ли картинка на странице, или нет")
    public Boolean getDisplayImageStatus() {
        waitElementDisplays(authenticatedImage, driver);
        return authenticatedImage.isDisplayed();
    }
}
