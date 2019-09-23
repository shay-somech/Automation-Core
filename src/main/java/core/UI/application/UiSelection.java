package core.UI.application;

import core.constants.PlatformType;

public class UiSelection {

    private PlatformType platform;
    private PlatformType secondPlatform;
    private String device;
    private String secondDevice;
    private String app;
    private boolean noReset;

    public UiSelection() {

    }

    public PlatformType getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformType platform) {
        this.platform = platform;
    }

    public PlatformType getSecondPlatform() {
        return secondPlatform;
    }

    public void setSecondPlatform(PlatformType secondPlatform) {
        this.secondPlatform = secondPlatform;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSecondDevice() {
        return secondDevice;
    }

    public void setSecondDevice(String secondDevice) {
        this.secondDevice = secondDevice;
    }

    public boolean isNoReset() {
        return noReset;
    }

    public void setNoReset(boolean noReset) {
        this.noReset = noReset;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
