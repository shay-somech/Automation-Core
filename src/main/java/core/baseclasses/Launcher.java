package core.baseclasses;

import core.UI.controller.tab.Tab1Controller;
import core.managers.AutomationLauncher;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.Log;
import core.utils.TestReporter;

public class Launcher {

    public static void start() {
        Log.info("Initializing Automation");
        AutomationLauncher.start();
    }

    public static void tearDown() {
        Log.info("Quiting Automation");
        DriverManager.getDriver().quit();
        new TestReporter().presentAllureReports(Tab1Controller.isGenerateReports);
    }
}
