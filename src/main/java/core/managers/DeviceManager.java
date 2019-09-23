package core.managers;

import core.UI.application.UiSelection;

class DeviceManager {

    private UiSelection uiSelection = UiSelection.getInstance();

    String getDeviceID() {
        if (uiSelection.getDevice().isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return uiSelection.getDevice();
        }
    }

    String getSecondDeviceID() {
        if (uiSelection.getSecondDevice().isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return uiSelection.getSecondDevice();
        }
    }
}
