package alert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class WriterAlert {
    public static void writeMessageAlert(WebDriver driver,String message) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(message);
        driver.switchTo().alert().accept();
    }
}
