package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static waits.Waiting.waitElementDisplays;

public class MainPage {
    private WebDriver driver;
    private String errorMessagePath = "//*[@class=\"alert alert-danger ng-binding ng-scope\"]";
    private String positiveResponseLocator = "//h1[@class=\"ng-scope\"]";
    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement username0;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password;
    @FindBy(id = "formly_1_input_username_0")
    private WebElement username1;
    @FindBy(xpath = "//*[@class=\"btn btn-danger\"]")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@class=\"alert alert-danger ng-binding ng-scope\"]")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@class=\"help-block ng-active\"]/descendant::div")
    private WebElement voidError;
    @FindBy(xpath = "//*[contains(@class,\"-valid-minlength ng-invalid ng-invalid-required\")]")
    private WebElement voidErrorTwo;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enteringParameters(String log, String pas, String des) {
        username0.sendKeys(log);
        password.sendKeys(pas);
        username1.sendKeys(des);
        loginButton.click();
    }

    /**
     * Ввод в поле username(0) значения “angular”
     * Ввод в поле password значения “password”
     * Ввод в поле username(1) значения “Фреймворк Ангуляр”
     * Нажать на кнопку Login.
     *
     * @throws Exception
     */
    public void authorizationCorrectParameters(String log, String pas, String des) throws Exception {
        waitElementDisplays(username0, driver);
        enteringParameters(log, pas, des);
    }

    /**
     * Ожидание:
     * Переход на новую страницу.
     * Появления на новой странице блока с надписью “Home You’re logged in!!”
     *
     * @return Возвращает текст блока на новой странице, если переход был совершён
     */
    public String getAuthorizationMessage() throws Exception {
        return driver.findElement(By.xpath(positiveResponseLocator)).getText();
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
    public String authorizationINcorrectLoginOrPassword(String lg, String ps, String descr) throws Exception {
        waitElementDisplays(username0, driver);
        enteringParameters(lg, ps, descr);
        waitElementDisplays(errorMessage, driver);
        return driver.findElement(By.xpath(errorMessagePath)).getText();
    }

    /**
     * Ввод значений в поля:
     * password/username(1) → password/Фреймворк Ангуляр
     * username(0)/username(1) → angular/Фреймворк Ангуляр
     * username(0)/password → angular/password
     *
     * @return Если выводились блоки с ошибками, то всё ок. Если нет, то тест возвращает ошибку
     */
    public String authorizationWhithVoidParam(String lg, String ps, String descr, String path) {
        ArrayList<WebElement> elements = new ArrayList<WebElement>(3);
        elements.add(username0);
        elements.add(password);
        elements.add(username1);
        elements.get(0).sendKeys(lg);
        elements.get(1).sendKeys(ps);
        elements.get(2).sendKeys(descr);
        password.click();
        if (driver.findElement(By.xpath(path)).isDisplayed() || loginButton.isEnabled() == false) {
            return driver.findElement(By.xpath(path)).getText();
        }
        return null;
    }
}
