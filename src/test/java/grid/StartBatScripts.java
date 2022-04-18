package grid;

import java.io.IOException;

public class StartBatScripts {
    public static void startHub() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\startHub.bat");
    }

    public static void startFirstNode() throws IOException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\StartNodeOne.bat");
    }

    public static void startSecondNode() throws IOException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\StartNodeTwo.bat");
    }
}