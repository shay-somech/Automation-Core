package core.utils;

import core.configurationPopUp.MainRunner;
import core.managers.baseclasses.DeviceManager;

public class AutomationStates {

    public enum AutomationState {
        START_WITH_UI, START_WITHOUT_UI
    }

    public static void runAutomation(AutomationState state, DeviceManager.Platform platform) {
        switch (state) {
            case START_WITH_UI:
                MainRunner.main(null);
                break;

            case START_WITHOUT_UI:
                DeviceManager.setPlatform(platform);
                break;
        }
    }

}
