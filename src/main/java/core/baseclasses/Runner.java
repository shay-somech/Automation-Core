package core.baseclasses;

import core.managers.AutomationLauncher;
import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Runner {

    public static void main(String[] args) {
        start();
    }

    @BeforeSuite
    public static void start() {
        MyLogger.logSysInfo("Initializing Automation");
        AutomationLauncher.start();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        MyLogger.logSysInfo("Quiting Automation");
        DriverManager.getDriver().quit();
        ActionHelper.quit();
        DriverServiceBuilder.killDriver();
    }
}
