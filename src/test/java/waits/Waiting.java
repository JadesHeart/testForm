package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс для явного ожидания
 * Ждёт в течении 20 секунд что элемент появится на странице
 * Для этого нужно передать драйвер и локатор элемента
 */
public class Waiting {
    /**
     * Вызываю класс WebDriverWait и засовываю его в переменную wait
     * Передаю драйвер и время для проверки
     *
     * @param driver драйвер
     * @return Возвращает найденный элемент, если он появился на странице
     */
    public static WebElement waitElementDisplays(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

}
