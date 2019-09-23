package core.managers;

import core.UI.application.Main;
import core.UI.application.UiSelection;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;

public class AutomationLauncher {

    private DriverServiceBuilder serviceBuilder = new DriverServiceBuilder();
    private UiSelection uiSelection = UiSelection.getInstance();

    public void start() {
        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            Log.info("Starting Automation From Jenkins");
            serviceBuilder.startAppiumServer();
            serviceBuilder.createJenkinsDriver(JenkinsManager.getInstance().getJenkinsSelectedPlatform());
            ActionHelper.getInstance();

        } else {
            Log.info("Starting Automation Manually");
            Main.main(null);
            serviceBuilder.startAppiumServer();
            serviceBuilder.createDriver(uiSelection.getPlatform());
            ActionHelper.getInstance();
        }
    }
}
