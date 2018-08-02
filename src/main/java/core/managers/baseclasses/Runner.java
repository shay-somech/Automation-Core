package core.managers.baseclasses;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Runner {

    @BeforeTest
    public void setUp() {
        MyLogger.logSys("Setting Up Automation \n");
        DriverManager.getDriver();
        ActionHelper.getInstance();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        MyLogger.logSys("Quiting Automation");
        DriverManager.getDriver().quit();
        ActionHelper.quit();
        DriverServiceBuilder.killDriver();
    }
}
