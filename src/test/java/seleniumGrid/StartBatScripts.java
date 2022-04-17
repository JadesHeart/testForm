package seleniumGrid;

import java.io.IOException;

public class StartBatScripts {
    public static void startHub() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c C:\\Users\\лали\\IdeaProjects\\testAngularjsProtractor\\src\\test\\resources\\BatFiles\\startHub.bat");
    }

    public static void startFirstNode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c C:\\Users\\лали\\IdeaProjects\\testAngularjsProtractor\\src\\test\\resources\\BatFiles\\StartNodeOne.bat");
    }

    public static void startSecondNode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c C:\\Users\\лали\\IdeaProjects\\testAngularjsProtractor\\src\\test\\resources\\BatFiles\\StartNodeTwo.bat");
    }
}
