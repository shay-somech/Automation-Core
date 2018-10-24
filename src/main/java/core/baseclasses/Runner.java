package core.baseclasses;

import core.managers.AutomationLauncher;
import core.utils.Log;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.TestReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class Runner {

    @BeforeSuite
    public static void start() {
        Log.info("Initializing Automation");
        AutomationLauncher.start();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Log.info("Quiting Automation");
        DriverManager.getDriver().quit();
        ActionHelper.quit();
        DriverServiceBuilder.killDriver();
//        TestReporter.presentAllureReports();
    }
}
