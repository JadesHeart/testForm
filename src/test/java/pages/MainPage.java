package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static propertiesConstants.PropertiesReader.getProperies;
import static waits.Waiting.waitResponse;

public class MainPage {
    private WebDriver driver;
    private String usernamePath = "//*[@id=\"username\"]";
    private String positiveResponseLocator = "//h1[@class=\"ng-scope\"]";
    private String erroreMessage = "//*[@class=\"alert alert-danger ng-binding ng-scope\"]";
    private String voidErrore = "//*[@class=\"help-block ng-active\"]/descendant::div";
    private String voidErroreTwo = "//*[@id=\"formly_1_input_username_0_description\"]";
    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement username0;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password;
    @FindBy(xpath = "//*[@id=\"formly_1_input_username_0\"]")
    private WebElement username1;
    @FindBy(xpath = "//*[@class=\"btn btn-danger\"]")
    private WebElement loginButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Ввод в поле username(0) значения “angular”
     * Ввод в поле password значения “password”
     * Ввод в поле username(1) значения “Фреймворк Ангуляр”
     * Нажать на кнопку Login.
     *
     * @throws Exception
     */
    public void authorizationCorrectParameters() throws Exception {
        waitResponse("//*[@id=\"username\"]", driver).isDisplayed();
        username0.click();
        username0.sendKeys(getProperies("login"));
        password.click();
        password.sendKeys(getProperies("password"));
        username1.click();
        username1.sendKeys(getProperies("description"));
        loginButton.click();
    }

    /**
     * Ожидание:
     * Переход на новую страницу.
     * Появления на новой странице блока с надписью “Home You’re logged in!!”
     *
     * @return Возвращает текст блока на новой странице, если переход был совершён
     */
    public String checkingAuthorization() throws Exception {
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
    public String authorizationINcorrectLogin() throws Exception {
        waitResponse(usernamePath, driver).isDisplayed();
        username0.click();
        username0.sendKeys(getProperies("falseLogin"));
        password.click();
        password.sendKeys(getProperies("password"));
        username1.click();
        username1.sendKeys(getProperies("description"));
        loginButton.click();
        waitResponse(erroreMessage, driver).isDisplayed();
        return driver.findElement(By.xpath(erroreMessage)).getText();

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
    public String authorizationINcorrectPassword() throws Exception {
        waitResponse(usernamePath, driver).isDisplayed();
        username0.click();
        username0.sendKeys(getProperies("login"));
        password.click();
        password.sendKeys(getProperies("falsePassword"));
        username1.click();
        username1.sendKeys(getProperies("description"));
        loginButton.click();
        waitResponse(erroreMessage, driver).isDisplayed();
        return driver.findElement(By.xpath(erroreMessage)).getText();
    }

    /**
     * Ввод значений в поля:
     * password/username(1) → password/Фреймворк Ангуляр
     * username(0)/username(1) → angular/Фреймворк Ангуляр
     * username(0)/password → angular/password
     *
     * @return Если выводились блоки с ошибками, то всё ок. Если нет, то тест возвращает ошибку
     */
    public String authorizationWhithVoidParam() {
        try {
            ArrayList<WebElement> elements = new ArrayList<WebElement>(3);
            elements.add(username0);
            elements.add(password);
            elements.add(username1);
            elements.get(0).sendKeys(" ");
            elements.get(1).sendKeys(getProperies("voidText"));
            elements.get(2).sendKeys(getProperies("voidText"));
            waitResponse(voidErrore, driver).isDisplayed();
            driver.navigate().refresh();
            elements.get(0).sendKeys(getProperies("voidText"));
            elements.get(1).sendKeys(" ");
            elements.get(2).sendKeys(getProperies("voidText"));
            waitResponse(voidErrore, driver).isDisplayed();
            driver.navigate().refresh();
            elements.get(0).sendKeys(getProperies("voidText"));
            elements.get(1).sendKeys(getProperies("voidText"));
            elements.get(2).sendKeys(" ");
            username0.click();
            waitResponse(voidErroreTwo, driver).isDisplayed();
            return "all work!";
        } catch (Exception e) {
            return "Не появились мэсенджи с тем, что поле пустое";
        }

    }

}
