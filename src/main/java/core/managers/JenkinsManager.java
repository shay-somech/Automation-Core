package core.managers;

import static core.utils.AndroidHelper.getAndroidDevices;
import static core.utils.IOSHelper.getIOSDevices;

public class JenkinsManager {

    private static JenkinsManager instance;
    private String jenkinsPlatformProperty;
    private boolean isJenkinsAndroidPlatform;
    private boolean isJenkinsIOSPlatform;
    private Boolean isBuildingFromJenkins = null;

    private JenkinsManager() {
        getJenkinsSelectedPlatform();
        getJenkinsDeviceId();
    }

    public static JenkinsManager getInstance() {
        if (instance == null) {
            instance = new JenkinsManager();
        }
        return instance;
    }

    public boolean isBuildingFromJenkins() {
        if (isBuildingFromJenkins == null) {
            jenkinsPlatformProperty = System.getProperty("JenkinsPlatform", "Android");
            isBuildingFromJenkins = jenkinsPlatformProperty.equals("Android") || jenkinsPlatformProperty.equals("iOS");
        }
        return isBuildingFromJenkins;
    }

    private void getJenkinsSelectedPlatform() {
        if (isBuildingFromJenkins) {

            switch (jenkinsPlatformProperty) {
                case "Android":
                    isJenkinsAndroidPlatform = true;
                    return;

                case "iOS":
                    isJenkinsIOSPlatform = true;
                    return;

                default:
            }
        } else {
            throw new RuntimeException("Cannot define Jenkins selected Platform");
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
