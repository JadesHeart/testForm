package scripts;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;


public class JavaScriptMethods {
    private JavascriptExecutor js;
    private MainPage mainPage;

    public JavaScriptMethods(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        mainPage = new MainPage(driver);
    }

    @Step("С помощью JS скрипта убирает курсор с поля описания")
    public void removeCursor(WebElement element) {
        js.executeScript("arguments[0].blur()",element);
    }

    @Step("Проверяет наличие скрола на странице. Если его нет вовзращает false")
    public Boolean checkScroll(WebElement body) {
        return (Boolean) js.executeScript("return arguments[0].scrollHeight>arguments[0].offsetHeight", body);
    }
}