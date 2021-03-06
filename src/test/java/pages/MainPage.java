package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiting.waitElementDisplays;

public class MainPage{
    private WebDriver driver;
    @FindBy(css = "body")
    private WebElement body;
    @FindBy(id = "username")
    private WebElement username0;
    @FindBy(id = "profile.password")
    private WebElement password;
    @FindBy(id = "formly_1_input_username_0")
    private WebElement description;
    @FindBy(css = ".form-actions > button")
    private WebElement loginButton;
    @FindBy(css = "[ng-if=\"Auth.error\"]")
    private WebElement errorMessage;
    @FindBy(xpath = "//h1[@class=\"ng-scope\"]")
    private WebElement positiveResponseLocator;
    @FindBy(css = ".formly-field .has-error")
    private WebElement nullDescriptionError;
    @FindBy(css = "[ng-messages*=\"username\"]")
    private WebElement nullLog;
    @FindBy(css = "[ng-messages*=\"password\"]")
    private WebElement nullPas;
    @FindBy(css = "nonExistentElement")
    private WebElement nonExistentElement;
    @FindBy(css = "[for=\"username\"]")
    private WebElement headlineUserName;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод данных о пользователе в форму")
    public void enteringParameters(String login, String password, String description) {
        waitElementDisplays(username0, driver);
        username0.sendKeys(login);
        this.password.sendKeys(password);
        this.description.sendKeys(description);
    }

    @Step("Клик на кнопку 'LogIn'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик на поле с паролем.")
    public void clickPassword() {
        password.click();
    }

    @Step("Проверка на работоспособность кнопки.")
    public Boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    /**
     * Ожидание:
     * Переход на новую страницу.
     * Появления на новой странице блока с надписью “Home You’re logged in!!”
     *
     * @return Возвращает текст блока на новой странице, если переход был совершён
     */
    @Step("Ожидание элемента успешной авторизации")
    public String getPositiveResponseText() {
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
    @Step("Ожидает сообщения об ошибке, вследствии неверного паролья/логина")
    public String getErrorMessageText() {
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
    @Step("Ожидает сообщение об ошибке пустого поля 'username description'")
    public String getErrorTextFromEmptyDescription() {
        waitElementDisplays(nullDescriptionError, driver);
        return nullDescriptionError.getText();
    }

    @Step("Ожидает сообщение об ошибке пустого поля 'Username'")
    public String getErrorTextFromEmptyLogin() {
        waitElementDisplays(nullLog, driver);
        return nullLog.getText();
    }

    @Step("Ожидает сообщение об ошибке пустого поля 'Password'")
    public String getErrorTextFromEmptyPassword() {
        waitElementDisplays(nullPas, driver);
        return nullPas.getText();
    }

    @Step("Попытка получить текст из несуществующего элемента")
    public String getTextFromNonExistentElement() {
        return nonExistentElement.getText();
    }

    @Step("Получаю текст из заголовка поля UserName")
    public String getTextFromHeadlineUserName() {
        return headlineUserName.getText();
    }

    @Step("Возвращает поле описание")
    public WebElement getDescription() {
        return description;
    }
     @Step("Возвращает тело страницы")
    public WebElement getBody() {
        return body;
    }
}
