package listener;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import tests.BaseTestClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FailureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            captureScreenShot(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "PNG Attachment {0}", type = "image/png")
    private byte[] captureScreenShot(ITestResult result) throws IOException {

        Object currentClass = result.getInstance();
        WebDriver webDriver = ((BaseTestClass) currentClass).getDriver();

        BufferedImage image = new AShot().takeScreenshot(webDriver).getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }
}