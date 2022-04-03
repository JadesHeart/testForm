package pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;

import static waits.Waiting.waitElementDisplays;

public class DummyRegistration {
    private WebDriver driver;
    @FindBy(css = "[href=\"#login\"]")
    private WebElement buttonToGoAuthorization;
    @FindBy(xpath = "//*[@id=\"alert1\"]/following-sibling::fieldset//descendant::input[@name=\"username\"]")
    private WebElement loginInput;
    @FindBy(xpath = "//*[@id=\"alert1\"]/following-sibling::fieldset//descendant::input[@name=\"password\"]")
    private WebElement passwordInput;
    @FindBy(css = ":nth-child(7) > div.span_1_of_4 > input")
    private WebElement submitButton;

    public DummyRegistration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Включает форму авторизиции
     */
    public void goToAuthorization() {
        waitElementDisplays(buttonToGoAuthorization, driver);
        buttonToGoAuthorization.click();
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

    /**
     * Создавёт файл и записывает туда куки сессии
     */
    public void setCookiesInFile() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        file.delete();
        file.createNewFile();
        FileWriter fileWrite = new FileWriter(file);
        BufferedWriter Bwrite = new BufferedWriter(fileWrite);
        Bwrite.write(getCookiesFromSite());
        Bwrite.newLine();
        Bwrite.close();
        fileWrite.close();
    }

    /**
     * Берёт куки из файла, в который записали их при первой авторизации
     */
    public String getCookiesFromFile() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        FileReader fileReader = new FileReader(file);
        BufferedReader Buffreader = new BufferedReader(fileReader);
        String sessionId = Buffreader.readLine();
        return sessionId;
    }

    /**
     * добавляет куки на страницу и обновляет страницу, чтобы они применились
     */
    public void addingCookies(String sessionId) {
        Cookie cookie = new Cookie("PHPSESSID", sessionId);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    /**
     * Берёт куки со страницы в настоящий момент
     */
    public String getCookiesFromSite() {
        return driver.manage().getCookieNamed("PHPSESSID").getValue();
    }
}