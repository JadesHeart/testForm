package tests;

import grid.InvalidResponseFromServer;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;

import static grid.StartBatScripts.startHub;
import static grid.StartBatScripts.startNode;
import static waits.Waiting.waitPositiveResponse;

public class BaseTestClass {
    @BeforeSuite
    @Parameters("name")
    public void startGrid(String name) throws InvalidResponseFromServer, IOException {
        if (name == "Grid") {
            startHub();
            waitPositiveResponse("http://localhost:4444/");
            startNode();
            waitPositiveResponse("http://localhost:5555/");
        }
    }
}