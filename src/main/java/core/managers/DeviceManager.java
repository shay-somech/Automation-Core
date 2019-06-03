package core.managers;

import core.UI.controller.tab.Tab1Controller;

class DeviceManager {

    static String getDeviceID() {
        if (Tab1Controller.device == null) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return Tab1Controller.device;
        }
    }

    static String getSecondDeviceID() {
        if (Tab1Controller.device2 == null) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return Tab1Controller.device2;
        }
    }
}
