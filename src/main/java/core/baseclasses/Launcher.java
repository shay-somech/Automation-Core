package core.baseclasses;

import core.managers.AutomationLauncher;
import core.managers.drivers.DriverManager;
import core.utils.Log;

import static core.managers.drivers.DriverServiceBuilder.stopAppiumServer;

public class Launcher {

    public static void start() {
        Log.info("Initializing Automation");
        new AutomationLauncher();
    }

    public static void tearDown() {
        Log.info("Quiting Automation");
        DriverManager.getDriver().quit();
        stopAppiumServer();
    }
}
