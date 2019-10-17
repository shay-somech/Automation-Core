package core.managers;

import static core.UI.controller.main.MainView.uiSelections;

class DeviceManager {

    String getDeviceID() {
        String device = (String) uiSelections.get("Device");
        if (device.isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }
}
