package cookies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCookies {
    private static WebDriver driver;

    public WriteCookies(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void setCookiesInFile() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try {
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);
            Bwrite.write(driver.manage().getCookieNamed("PHPSESSID").getValue());
            Bwrite.newLine();
            Bwrite.close();
            fileWrite.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
