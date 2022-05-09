package tests;

import grid.InvalidResponseFromServer;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;

import static grid.StartBatScripts.*;
import static waits.Waiting.waitPositiveResponse;

public class BaseTestClass {
    @BeforeSuite
    @Parameters({"name", "browserName"})
    public void startGrid(String name, String browserName) throws InvalidResponseFromServer, IOException {
        if (name == "Grid") {
            startHub();
            waitPositiveResponse("http://localhost:4444/");
            setBrowserName(browserName);
            startNode();
            waitPositiveResponse("http://localhost:5555/");
        }
    }
}