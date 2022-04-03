package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class AuthorizationPageSQLSite {
    private WebDriver driver;
    @FindBy(css = "[type=text]")
    private WebElement loginInput;
    @FindBy(css = "[type=password]")
    private WebElement passwordInput;
    @FindBy(css = "[type=submit]")
    private WebElement submitButton;
    @FindBy(css = "[href=\"/personal.php\"]")
    private WebElement profile;

    public AuthorizationPageSQLSite(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Вводит логин и пароль в форму
     *
     * @param login
     * @param password
     */
    public void enteringParameters(String login, String password) {
        waitElementDisplays(loginInput, driver);
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
    }

    /**
     * Кликает на кнопку авторизации
     */
    public void clickLoginButton() {
        submitButton.click();
    }

    public String getProfileName() {
        return profile.getText();
    }
}