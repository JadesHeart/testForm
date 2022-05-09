package grid;

import java.io.FileWriter;
import java.io.IOException;

public class StartBatScripts {
    public static void startHub() throws IOException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\startHub.bat");
    }

    public static void setBrowserName(String browserName) {
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\src\\test\\resources\\BatFiles\\StartNodeOne.bat", false)) {
            writer.write("java -Dwebdriver.chrome.driver=\"" + browserName + "\" -jar selenium-server-standalone-3.5.0.jar -role webdriver -hub http://192.168.0.11:4444/grid/register -port 5555");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void startNode() throws IOException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\StartNodeOne.bat");
    }

}