package listener;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.FormsTest;

public class testFailure implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        getErrorScreenshot();
    }
    /**
     * Ввожу НЕ верные параметры(пароль) и получаю ошибку
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] getErrorScreenshot() {
        return ((TakesScreenshot) FormsTest.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

