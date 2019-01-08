package core.managers;

import core.UI.controller.tab.Tab1Controller;

import static core.managers.JenkinsManager.JenkinsProperty.DEVICE_ID;
import static core.managers.JenkinsManager.JenkinsProperty.JENKINS_INSTANCE;

class DeviceManager {

    private static String deviceID;
    private static String deviceID2;

    static String getDeviceID() {
        if (deviceID == null) {
            if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
                deviceID = (String) JenkinsManager.getInstance().getJenkinsSelection(DEVICE_ID);
            } else {
                deviceID = Tab1Controller.device;
            }
        }
        return deviceID;
    }

    static String getSecondDeviceID() {
        if (deviceID2 == null) {
            if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
                deviceID2 = (String) JenkinsManager.getInstance().getJenkinsSelection(DEVICE_ID);
            } else {
                deviceID2 = Tab1Controller.device2;
            }
        }
        return deviceID2;
    }
}
