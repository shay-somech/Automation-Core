package core.managers;

import static core.utils.AndroidHelper.getAndroidDevices;
import static core.utils.IOSHelper.getIOSDevices;

public class JenkinsManager {

    private static JenkinsManager instance;
    private String jenkinsPlatformProperty;
    private boolean isJenkinsAndroidPlatform;
    private boolean isJenkinsIOSPlatform;

    private JenkinsManager() {
        getJenkinsSelectedPlatform();
    }

    public static JenkinsManager getInstance() {
        if (instance == null) {
            instance = new JenkinsManager();
        }
        return instance;
    }

    public boolean isBuildingFromJenkins() {
        jenkinsPlatformProperty = System.getProperty("JenkinsPlatform");
        if (jenkinsPlatformProperty == null) {
            return false;
        }
        return jenkinsPlatformProperty.equals("Android") || jenkinsPlatformProperty.equals("iOS");
    }

    private void getJenkinsSelectedPlatform() {
        if (isBuildingFromJenkins()) {

            switch (jenkinsPlatformProperty) {
                case "Android":
                    isJenkinsAndroidPlatform = true;
                    return;

                case "iOS":
                    isJenkinsIOSPlatform = true;
                    return;

                default:
            }
        }
    }

    public String getJenkinsDeviceId() {
        if (isJenkinsAndroidPlatform) {
            getAndroidDevices();

        } else if (isJenkinsIOSPlatform) {
            getIOSDevices();

        } else {
            throw new RuntimeException("Cannot get desired Device");
        }
        return null;
    }
}
