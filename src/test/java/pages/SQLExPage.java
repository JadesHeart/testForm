package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class SQLExPage {
    private WebDriver driver;
    @FindBy(name = "profile.login")
    private WebElement loginInput;
    @FindBy(name = "psw")
    private WebElement passwordInput;
    @FindBy(name = "subm1")
    private WebElement submitButton;
    @FindBy(css = "[href=\"/personal.php\"]")
    private WebElement profile;

    public SQLExPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Вводит логин и пароль в форму
     *
     * @param login
     * @param password
     */
    @Step("Авторизация через логин и пароль")
    public void enteringParameters(String login, String password) {
        waitElementDisplays(loginInput, driver);
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
    }

    /**
     * Кликает на кнопку авторизации
     */
    @Step("Клик на кнопку входа")
    public void clickLoginButton() {
        submitButton.click();
    }

    @Step("Получает текст имени аккаунта")
    public String getProfileName() {
        return profile.getText();
    }
}