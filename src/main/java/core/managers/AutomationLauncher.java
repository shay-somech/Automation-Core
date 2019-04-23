package core.managers;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.UI.controller.tab.Tab2Controller;
import core.constants.AutomationStates;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;
import core.utils.TestNGHelper;
import org.testng.xml.XmlSuite;

import static core.constants.AutomationStates.*;
import static core.managers.JenkinsManager.JenkinsProperty.JENKINS_INSTANCE;

public class AutomationLauncher {

    private static void startAutomationState(AutomationStates states) {
        switch (states) {
            case MANUAL:
                Main.main(null);
                DriverServiceBuilder.createAppiumService();
                DriverServiceBuilder.createDriver(Tab1Controller.platform);
                ActionHelper.getInstance();
                break;

            case MANUAL_PARALLEL:
                Main.main(null);
                DriverServiceBuilder.createAppiumService();
                DriverServiceBuilder.createDriver(Tab1Controller.platform);
                DriverServiceBuilder.createDriver(Tab1Controller.platform2);

                try {
                    new TestNGHelper(Tab2Controller.suiteName,
                            Tab2Controller.testName,
                            "Default Device ID",
                            "Default Device Name",
                            Class.forName(Tab2Controller.testClassName),
                            Tab2Controller.verbose,
                            Tab2Controller.threadCount,
                            XmlSuite.ParallelMode.METHODS).createTestNGXml();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ActionHelper.getInstance();
                break;

            case JENKINS_PARAMETERIZED:
                DriverServiceBuilder.createAppiumService();
                DriverServiceBuilder.createJenkinsDriver();
                ActionHelper.getInstance();
                break;
        }
    }

    public static void start() {
        if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
            Log.info("Starting Automation From Jenkins");
            startAutomationState(JENKINS_PARAMETERIZED);

//        } else if (Tab2Controller.isParallelRun) {
//            Log.info("Starting Parallel Automation Manually");
//            startAutomationState(MANUAL_PARALLEL);

        } else {
            Log.info("Starting Automation Manually");
            startAutomationState(MANUAL);
        }

    }
}
