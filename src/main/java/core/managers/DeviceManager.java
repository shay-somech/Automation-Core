package core.managers;

import static core.UI.controller.MainController.uiSelection;

class DeviceManager {

    String getDeviceID() {
        String device = (String) uiSelection.get("Device");

        if (device.isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }

    String getSecondDeviceID() {
        String device = (String) uiSelection.get("Second Device");

        if (device.isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }
}
