package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AlertPage;
import properties.ReadProperties;

import static alert.WriterAlert.writeMessageAlert;

public class AlertTest extends BaseTestClass {
    private AlertPage alertPage;

    @BeforeTest
    public void startBrowser() {
        alertPage = new AlertPage(driver);
        driver.get(ReadProperties.getProperty("browserAlertURL"));
    }

    @Description(value = "Тест проверяет что кастомный текст применился в алерт")
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Тестирования пользовательского интерфейса")
    @Feature(value = "Тестирование алерта")
    @Story(value = "Ввод сообщения в алерт и нажатия кнопки ввода")
    @Test
    public void testInputTextInAlert() {
        alertPage.clickInputAlertButtonButton();
        alertPage.windowEntry();
        alertPage.clickDisplayedAlertButton();
        writeMessageAlert(driver, ReadProperties.getProperty("alertMessage"));
        Assert.assertEquals(alertPage.getAlertsMessage(), ReadProperties.getProperty("returnedAlertMessage"), ReadProperties.getProperty("alertFailedMessage"));
    }
}
