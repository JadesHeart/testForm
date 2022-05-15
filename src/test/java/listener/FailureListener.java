package listener;

import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import tests.BaseTestClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FailureListener extends BaseTestClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            createAttachment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Attachment(value = "PNG Attachment {0}", type = "image/png")
    public byte[] createAttachment() throws IOException {
        return captureScreenShot();
    }

    private byte[] captureScreenShot() throws IOException {
        BufferedImage image = new AShot().takeScreenshot(driver).getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }
}