package webdriver;

/**
 * Save data for WebDriver
 */
public class ChromeDriver {
    /**
     * Directory chromedriver: \webDriver\chromedriver\chromedriver.exe
     * For ChromeVersion 97.0.4692.71
     *
     * @return new ChromeDriver
     */
    public static org.openqa.selenium.chrome.ChromeDriver getChromeDriver() {
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "\\webDriver\\chromedriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return new org.openqa.selenium.chrome.ChromeDriver();
    }
}
