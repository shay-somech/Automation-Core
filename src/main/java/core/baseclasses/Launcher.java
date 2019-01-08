package core.baseclasses;

import core.managers.AutomationLauncher;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.Log;

public class Launcher {

    public static void start() {
        Log.info("Initializing Automation");
        AutomationLauncher.start();
    }

    public static void tearDown() {
        Log.info("Quiting Automation");
        DriverManager.getDriver().quit();
        DriverServiceBuilder.killDriver();
    }
}
