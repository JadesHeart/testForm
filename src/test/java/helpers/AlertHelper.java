package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHelper {
    public static void writeMessageAlert(WebDriver driver,String message) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(message);
        driver.switchTo().alert().accept();
    }
}
