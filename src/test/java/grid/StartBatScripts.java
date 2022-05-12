package grid;

import java.io.IOException;

public class StartBatScripts {
    public static void startHub() throws IOException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\startHub.bat");
    }

    public static void startNode() throws IOException {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\StartNodeOne.bat");
    }

}