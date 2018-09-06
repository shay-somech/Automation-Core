package core.managers;

import static core.utils.AndroidHelper.getAndroidDevices;
import static core.utils.IOSHelper.getIOSDevices;

public class JenkinsManager {

    private static JenkinsManager instance;
    private String jenkinsPlatformProperty;
    private boolean isJenkinsAndroidPlatform;
    private boolean isJenkinsIOSPlatform;

    private JenkinsManager() {
        jenkinsPlatformProperty = System.getProperty("JenkinsPlatform");
    }

    public static JenkinsManager getInstance() {
        if (instance == null) {
            instance = new JenkinsManager();
        }
        return instance;
    }

    public boolean isBuildingFromJenkins() {
        return jenkinsPlatformProperty != null;
    }

    public String getJenkinsSelectedPlatform() {
        switch (jenkinsPlatformProperty) {
            case "Android":
                isJenkinsAndroidPlatform = true;
                return "Android";

            case "iOS":
                isJenkinsIOSPlatform = true;
                return "iOS";
        }

        throw new RuntimeException("Could not get selected platform from Jenkins");
    }

    public String getJenkinsDeviceId() {
        if (isJenkinsAndroidPlatform) {
            return getAndroidDevices().get(0);

        } else if (isJenkinsIOSPlatform) {
            return getIOSDevices().get(0);

        } else {
            throw new RuntimeException("Cannot get desired Device");
        }
    }
}
