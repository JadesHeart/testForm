package grid;

import java.io.IOException;

import static waits.Waiting.waitPositiveResponse;

public class StartBatScripts {
    public static void startHub() throws IOException, InvalidResponseFromServer {
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\startHub.bat");
        waitPositiveResponse("http://localhost:4444/");
    }

    public static void startFirstNode() throws IOException , InvalidResponseFromServer{
        Runtime.getRuntime().exec("cmd /c src\\test\\resources\\BatFiles\\StartNodeOne.bat");
        waitPositiveResponse("http://localhost:4444/");
    }
}