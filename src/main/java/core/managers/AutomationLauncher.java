package core.managers;

import core.UI.application.Main;
import core.UI.controller.main.MainView;
import core.constants.PlatformType;
import core.managers.drivers.DriverServiceBuilder;
import core.utils.ActionHelper;
import core.utils.Log;

import static core.UI.controller.main.MainView.uiSelections;

public class AutomationLauncher {

    private DriverServiceBuilder serviceBuilder = new DriverServiceBuilder();

    public void start() {
        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            Log.info("Starting Automation From Jenkins");
            serviceBuilder.startAppiumServer();
            serviceBuilder.createJenkinsDriver(JenkinsManager.getInstance().getJenkinsSelectedPlatform());
            ActionHelper.getInstance();

        } else {
            Log.info("Starting Automation Manually");

            MainView.uiSelections.forEach((key, value) -> Log.info(value.toString()));

            Main.main(null);
            serviceBuilder.startAppiumServer();
            serviceBuilder.createDriver((PlatformType) uiSelections.get("Platform"));
            ActionHelper.getInstance();
        }
    }
}
