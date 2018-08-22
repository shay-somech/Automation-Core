package core.managers;

import core.UI.MainUIRunner;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;

import static core.UI.MainUIRunner.selectPlatformChoiceBox;
import static core.constants.AutomationStatesValues.JENKINS_PARAMETERIZED;
import static core.constants.AutomationStatesValues.MANUAL;

public class AutomationLauncher {

    private static void startAutomationState(String states) {
        switch (states) {
            case MANUAL:
                MainUIRunner.main(null);
                DriverServiceBuilder.createDriver(selectPlatformChoiceBox);
                ActionHelper.getInstance();
//                runTestNGSuiteByClass(selectTestToRunChoiceBox.getSelectionModel().getSelectedItem());
                break;

            case JENKINS_PARAMETERIZED:
                DriverServiceBuilder.createJenkinsDriver();
                ActionHelper.getInstance();
                break;
        }
    }

    public static void start() {
        if (JenkinsManager.getInstance().isBuildingFromJenkins()) {
            MyLogger.logSys("Starting Automation From Jenkins");
            startAutomationState(JENKINS_PARAMETERIZED);
        } else {
            MyLogger.logSys("Starting Automation Manually");
            startAutomationState(MANUAL);
        }

    }
}
