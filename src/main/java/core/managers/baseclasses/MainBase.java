package core.managers.baseclasses;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.utils.ActionHelper;
import core.utils.AutomationStates;
import core.utils.SwipeHelper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static core.utils.AutomationStates.AutomationState.START_WITH_UI;

public class MainBase {

    @BeforeTest
    public void setUp() {
        // When starting Automation WITHOUT UI, please provide Platform name
        AutomationStates.runAutomation(START_WITH_UI, null);
        MyLogger.logSys("Setting Up Automation \n");
        ActionHelper.getInstance();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        MyLogger.logSys("Quiting Automation");
        DriverManager.getDriver().quit();
        SwipeHelper.quit();
    }
}
