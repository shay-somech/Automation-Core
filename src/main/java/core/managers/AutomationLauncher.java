package core.managers;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.constants.AutomationStates;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;

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

        } else if (Tab1Controller.isParallelRun) {
            Log.info("Starting Parallel Automation Manually");
            startAutomationState(MANUAL_PARALLEL);

        } else {
            Log.info("Starting Automation Manually");
            startAutomationState(MANUAL);
        }

    }
}
