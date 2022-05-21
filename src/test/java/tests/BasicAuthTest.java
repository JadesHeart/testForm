package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasicAuthPage;
import properties.ReadProperties;

public class BasicAuthTest extends BaseTestClass {
    private static BasicAuthPage basicAuthPage;

    @BeforeTest
    public void startBrowser() {
        basicAuthPage = new BasicAuthPage(driver);
        driver.get(ReadProperties.getProperty("browserBasicAuthURL"));
    }

    @Test
    public void testBasicAuth() {
        basicAuthPage.clickOnDisplayImage();
        Assert.assertTrue(basicAuthPage.getDisplayImageStatus(), ReadProperties.getProperty("basicAuthMessage"));
    }
}
