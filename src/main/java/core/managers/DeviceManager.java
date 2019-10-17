package core.managers;

import core.utils.Log;

import static core.UI.controller.tab.Tab1Controller.uiSelection;

class DeviceManager {

    static String getDeviceID() {
        String device = (String) uiSelection.get("Device");
        Log.info("Device ID == " + uiSelection.get("Device"));
        if (device == null) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
        }
    }
}
