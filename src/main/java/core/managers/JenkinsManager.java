package core.managers;

import static core.utils.AndroidHelper.getAndroidDevices;
import static core.utils.IOSHelper.getIOSDevices;

public class JenkinsManager {

    private static String jenkinsPlatformProperty;
    private static boolean isJenkinsAndroidPlatform;
    private static boolean isJenkinsIOSPlatform;
    public static boolean isBuildingFromJenkins = isBuildingFromJenkins();

    private JenkinsManager() {
        getJenkinsSelectedPlatform();
        getJenkinsDeviceId();
    }

    private static boolean isBuildingFromJenkins() {
        jenkinsPlatformProperty = System.getProperty("JenkinsPlatform", "Android");
        return isBuildingFromJenkins = jenkinsPlatformProperty.equals("Android") || jenkinsPlatformProperty.equals("iOS");
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

    public static String getJenkinsDeviceId() {
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
