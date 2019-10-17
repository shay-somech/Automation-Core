package core.managers;

import static core.UI.controller.tab.Tab1Controller.uiSelection;

class DeviceManager {

    static String getDeviceID() {
        String device = (String) uiSelection.get("Device");
        if (device == null) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }
}
