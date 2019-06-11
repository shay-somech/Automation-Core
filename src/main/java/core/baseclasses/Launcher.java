package core.baseclasses;

import core.managers.AutomationLauncher;
import core.managers.drivers.DriverManager;
import core.utils.Log;

public class Launcher {

    public void start() {
        Log.info("Initializing Automation");
        new AutomationLauncher().start();
    }

    public void tearDown() {
        Log.info("Quiting Automation");
        DriverManager.getDriver().quit();
    }
}
