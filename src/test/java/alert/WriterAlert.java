package alert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import properties.ReadProperties;

public class WriterAlert {
    public static void writeMessageAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(ReadProperties.getProperty("alertMessage"));
        driver.switchTo().alert().accept();
    }
}
