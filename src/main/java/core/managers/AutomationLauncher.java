package core.managers;

import core.UI.MainUIRunner;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;

import static core.UI.ComboBoxes.selectPlatformComboBox;
import static core.constants.AutomationStatesValues.JENKINS_PARAMETERIZED;
import static core.constants.AutomationStatesValues.MANUAL;

public class AutomationLauncher {

    private static void startAutomationState(String states) {
        switch (states) {
            case MANUAL:
                MainUIRunner.main(null);
                DriverServiceBuilder.createDriver(selectPlatformComboBox);
                ActionHelper.getInstance();
//                runTestNGSuiteByClass(selectTestToRunComboBox.getSelectionModel().getSelectedItem());
                break;

            case JENKINS_PARAMETERIZED:
                DriverServiceBuilder.createJenkinsDriver();
                ActionHelper.getInstance();
                break;
        }
    }

    public static void start() {
        if (JenkinsManager.getInstance().isBuildingFromJenkins()) {
            Log.info("Starting Automation From Jenkins");
            startAutomationState(JENKINS_PARAMETERIZED);
        } else {
            Log.info("Starting Automation Manually");
            startAutomationState(MANUAL);
        }

    }
}
