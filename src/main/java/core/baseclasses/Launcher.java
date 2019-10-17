package core.baseclasses;

import core.UI.application.Main;
import core.constants.PlatformType;
import core.managers.JenkinsManager;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.Log;

import static core.UI.controller.tab.Tab1Controller.uiSelection;

public class Launcher {

    private DriverServiceBuilder serviceBuilder = new DriverServiceBuilder();

    public void launchAutomationUI() {
        Main.main(null);
    }

    private void createDriverForManualSession() {
        Log.info("Starting Automation Manually");

        for (String key : uiSelection.keySet()) {
            Log.info("AFTER INIT " + key + " :: " + uiSelection.get(key));
        }

        serviceBuilder.createDriver((PlatformType) uiSelection.get("Platform"));
    }

    private void createDriverForJenkinsSession() {
        Log.info("Starting Automation From Jenkins");
        serviceBuilder.createJenkinsDriver(JenkinsManager.getInstance().getJenkinsSelectedPlatform());

    }


    public void start() {
        serviceBuilder.startAppiumServer();

        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            createDriverForJenkinsSession();
        }

        createDriverForManualSession();
    }

    public void tearDown() {
        Log.info("Quiting Automation");
        DriverManager.getDriver().quit();
    }
}
