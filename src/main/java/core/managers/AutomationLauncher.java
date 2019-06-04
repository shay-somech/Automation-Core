package core.managers;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;

public class AutomationLauncher {

    public void start() {
        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            Log.info("Starting Automation From Jenkins");
            DriverServiceBuilder.startAppiumServer();
            DriverServiceBuilder.createJenkinsDriver(JenkinsManager.getInstance().getJenkinsSelectedPlatform());
            ActionHelper.getInstance();

        } else {
            Log.info("Starting Automation Manually");
            Main.main(null);
            DriverServiceBuilder.startAppiumServer();
            DriverServiceBuilder.createDriver(Tab1Controller.platform);
            ActionHelper.getInstance();        }
    }
}
