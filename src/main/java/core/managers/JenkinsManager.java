package core.managers;

import static core.utils.AndroidHelper.getAndroidDevices;
import static core.utils.IOSHelper.getIOSDevices;

public class JenkinsManager {

    private static JenkinsManager instance;
    private String jenkinsPlatformProperty;
    private String jenkinsNoResetProperty;
    private boolean isJenkinsAndroidPlatform;
    private boolean isJenkinsIOSPlatform;

    private JenkinsManager() {
        jenkinsPlatformProperty = System.getProperty("JenkinsPlatform");
        jenkinsNoResetProperty = System.getProperty("JenkinsNoReset");
    }

    public static JenkinsManager getInstance() {
        if (instance == null) {
            instance = new JenkinsManager();
        }
        return instance;
    }

    public enum JenkinsProperty {
        JENKINS_INSTANCE, NO_RESET_PROPERTY, PLATFORM, DEVICE_ID
    }

    public Object getJenkinsSelection(JenkinsProperty property) {
        switch (property) {
            case JENKINS_INSTANCE:
                return isBuildingFromJenkins();

            case PLATFORM:
                return getJenkinsSelectedPlatform();

            case DEVICE_ID:
                return getJenkinsDeviceId();

            case NO_RESET_PROPERTY:
                return getJenkinsNoResetSelection();
        }
        throw new RuntimeException("Selected property is not defined in scope");
    }

    private boolean isBuildingFromJenkins() {
        return jenkinsPlatformProperty != null;
    }

    private String getJenkinsSelectedPlatform() {
        switch (jenkinsPlatformProperty) {
            case "Android":
                isJenkinsAndroidPlatform = true;
                return "Android";

            case "iOS":
                isJenkinsIOSPlatform = true;
                return "iOS";
        }

        throw new RuntimeException("Could not get selected selected Platform from Jenkins");
    }

    private String getJenkinsDeviceId() {
        if (isJenkinsAndroidPlatform) {
            return getAndroidDevices().get(0);

        } else if (isJenkinsIOSPlatform) {
            return getIOSDevices().get(0);

        } else {
            throw new RuntimeException("Cannot get desired Device");
        }
    }

    private boolean getJenkinsNoResetSelection() {
        if (jenkinsNoResetProperty.equals("true")) {
            return true;
        } else if (jenkinsNoResetProperty.equals("false")) {
            return false;
        }
        throw new RuntimeException("Could not get No Reset property selection");
    }
}
