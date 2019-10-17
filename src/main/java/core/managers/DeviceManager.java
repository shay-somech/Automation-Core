package core.managers;

import core.UI.application.UiSelection;

import static core.UI.controller.main.MainView.uiSelections;

class DeviceManager {

    private UiSelection uiSelection = UiSelection.getInstance();

    String getDeviceID() {
        String device = (String) uiSelections.get("Device");
        if (device.isEmpty()) {
            return JenkinsManager.getInstance().getJenkinsDeviceId();
        } else {
            return device;
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
