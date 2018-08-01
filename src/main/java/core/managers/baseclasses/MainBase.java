package core.managers.baseclasses;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.utils.ActionHelper;
import core.utils.SwipeHelper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class MainBase {

    @BeforeTest
    public void setUp() throws Exception {
        // When starting Automation WITHOUT UI, provide Platform name
//        AppiumServerManager.startAppiumServer("4723");
//        AutomationStates.runAutomation(START_WITHOUT_UI, DeviceManager.Platform.iOS);
        DriverManager.getDriver();
        MyLogger.logSys("Setting Up Automation \n");
        ActionHelper.getInstance();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws Exception {
        MyLogger.logSys("Quiting Automation");
        DriverManager.getDriver().quit();
        SwipeHelper.quit();
//        AppiumServerManager.stopAppiumServer("4723");
    }
}
