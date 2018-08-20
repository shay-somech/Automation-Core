package core.constants;

import java.io.File;

import static core.UI.MainUIRunner.selectAppToInstallChoiceBox;
import static core.managers.JenkinsManager.isBuildingFromJenkins;
import static core.utils.AndroidHelper.getAvailableAPKs;
import static core.utils.IOSHelper.getAvailableIPAs;

public class AppInfo {

    public static final String androidAppPackage = "com.yit.evritViewer";
    public static final String androidAppMainActivity = "com.yit.evrit.viewer.SplashActivity";
    public static final String iOSBundleId = "com.yit.evrit";
    public static final String androidApp = setAndroidAppInstallationPath();
    public static final String iOSApp = setIOSAppInstallationPath();

    public static final String email = "Automation@Gini-Apps.com";
    public static final String password = "automation";

    private static String setAndroidAppInstallationPath() {
        String apkAbsolutePath = null;

        if (isBuildingFromJenkins) {
            for (File apk : getAvailableAPKs("/src/main/resources/")) {
                apkAbsolutePath = apk.getAbsolutePath();
            }
            return apkAbsolutePath;
        } else {
            return selectAppToInstallChoiceBox.getValue();
        }
    }

    private static String setIOSAppInstallationPath() {
        String ipaAbsolutePath = null;

        if (isBuildingFromJenkins) {
            for (File apk : getAvailableIPAs("/src/main/resources/")) {
                ipaAbsolutePath = apk.getAbsolutePath();
            }
            return ipaAbsolutePath;
        } else {
            return selectAppToInstallChoiceBox.getValue();
        }
    }
}