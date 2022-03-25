package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class MainPage {
    private WebDriver driver;
    private String errorMessagePath = "//*[@class=\"alert alert-danger ng-binding ng-scope\"]";

    @FindBy(id = "username")
    private WebElement username0;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "formly_1_input_username_0")
    private WebElement username1;
    @FindBy(css = "div.form-actions > button")
    private WebElement loginButton;
    @FindBy(css = "div.alert.alert-danger.ng-binding.ng-scope")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@class=\"help-block ng-active\"]/descendant::div")
    private WebElement voidError;
    @FindBy(xpath = "//*[contains(@class,\"-valid-minlength ng-invalid ng-invalid-required\")]")
    private WebElement voidErrorTwo;
    @FindBy(xpath = "//h1[@class=\"ng-scope\"]")
    private WebElement positiveResponseLocator;
    @FindBy(xpath = "//*[contains(@class,\" ng-scope has-error\")]")
    private WebElement nullDescriptionError;
    @FindBy(xpath = "//*[@class=\"help-block ng-active\"]/descendant::div")
    private WebElement nullPasOrLogError;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enteringParameters(String login, String password, String description) {
        waitElementDisplays(username0, driver);
        username0.sendKeys(login);
        this.password.sendKeys(password);
        username1.sendKeys(description);
    }

    public void clickLoginButton() {loginButton.click();}

    /**
     * Ожидание:
     * Переход на новую страницу.
     * Появления на новой странице блока с надписью “Home You’re logged in!!”
     *
     * @return Возвращает текст блока на новой странице, если переход был совершён
     */
    public String getPositiveResponseText() throws Exception {
        waitElementDisplays(positiveResponseLocator, driver);
        return positiveResponseLocator.getText();
    }

    /**
     * Ввод в поле username значения “angular False”
     * Ввод в поле password значения “password”
     * Ввод в поле username(1) значение “Фреймворк Ангуляр”
     * Нажать на кнопку Login.
     * Получить ошибку
     *
     * @return текст ошибки, что логин или пароль неверен
     */
    public String getErrorTextFromFalseLogOrPas() {
        waitElementDisplays(errorMessage, driver);
        return errorMessage.getText();
    }

    /**
     * Ввод значений в поля:
     * password/username(1) → password/Фреймворк Ангуляр
     * username(0)/username(1) → angular/Фреймворк Ангуляр
     * username(0)/password → angular/password
     *
     * @return Если выводились блоки с ошибками, то всё ок. Если нет, то тест возвращает ошибку
     */
    public String getErrorTextFromVoidDescription() {
        password.click();
        loginButton.isEnabled();
        waitElementDisplays(nullDescriptionError, driver);
        return nullDescriptionError.getText();
    }

    public String getErrorTextFromVoidPasswordOrLogin() {
        password.click();
        loginButton.isEnabled();
        waitElementDisplays(nullPasOrLogError, driver);
        return nullPasOrLogError.getText();
    }
}
