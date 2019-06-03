package core.managers;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;

public class AutomationLauncher {

    public AutomationLauncher() {
        start();
    }

    private void start() {
        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            Log.info("Starting Automation From Jenkins");
            DriverServiceBuilder.createAppiumService();
            DriverServiceBuilder.createJenkinsDriver();
            ActionHelper.getInstance();

        } else {
            Log.info("Starting Automation Manually");
            Main.main(null);
            DriverServiceBuilder.startAppiumServer();
            DriverServiceBuilder.createDriver(Tab1Controller.platform);
            ActionHelper.getInstance();        }
    }
}
