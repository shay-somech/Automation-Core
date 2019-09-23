package core.baseclasses;

import core.UI.application.Main;
import core.UI.application.UiSelection;
import core.managers.JenkinsManager;
import core.managers.drivers.DriverManager;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.Log;

public class Launcher {

    private DriverServiceBuilder serviceBuilder = new DriverServiceBuilder();

    public void launchAutomationUI() {
        Main.main(null);
    }

    private void createDriverForManualSession() {
        Log.info("Starting Automation Manually");
        serviceBuilder.createDriver(UiSelection.getInstance().getPlatform());
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
