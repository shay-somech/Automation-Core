package core.baseclasses;

import core.UI.application.Main;
import core.constants.PlatformType;
import core.managers.JenkinsManager;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.Log;

import static core.UI.controller.main.MainView.UiSelections.PLATFORM;
import static core.UI.controller.main.MainView.uiSelection;

public class Launcher {

    private DriverServiceBuilder serviceBuilder = new DriverServiceBuilder();

    public void launchAutomationUI() {
        Main.main(null);
    }

    private void createDriverForManualSession() {
        Log.info("Starting Automation Manually");
        serviceBuilder.createDriver((PlatformType) uiSelection.get(PLATFORM));
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
