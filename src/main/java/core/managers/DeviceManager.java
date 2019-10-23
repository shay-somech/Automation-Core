package core.managers;

import static core.UI.controller.main.MainView.UiSelections.DEVICE;
import static core.UI.controller.main.MainView.UiSelections.SECOND_DEVICE;
import static core.UI.controller.main.MainView.uiSelection;

class DeviceManager {

    String getDeviceID() {
        String device = (String) uiSelection.get(DEVICE);

        if (device.isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }

    String getSecondDeviceID() {
        String device = (String) uiSelection.get(SECOND_DEVICE);

        if (device.isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }
}
