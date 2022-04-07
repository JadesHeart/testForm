package scripts;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptMethods {
    JavascriptExecutor js;

    public JavaScriptMethods(WebDriver driver) {
        js = (JavascriptExecutor) driver;
    }

    @Step("С помощью JS скрипта убирает курсор с поля описания")
    public void removeCursor(String locator) {
        js.executeScript("document.querySelector('" + locator + "').blur()");
    }

    @Step("Проверяет наличие скрола на странице. Если его нет вовзращает false")
    public Boolean checkScroll(String locator) {
        return (Boolean) js.executeScript("return document.querySelector('" + locator + "').scrollHeight>document.querySelector('" + locator + "').offsetHeight");
    }
}