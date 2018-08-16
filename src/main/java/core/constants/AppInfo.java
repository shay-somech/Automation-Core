package core.constants;

import static core.UI.MainUIRunner.selectAppToInstallChoiceBox;

public class AppInfo {

    public static String androidAppPackage = "com.yit.evritViewer";
    public static String androidAppMainActivity = "com.yit.evrit.viewer.SplashActivity";
    public static String androidAppInstallationPath = selectAppToInstallChoiceBox.getValue();
    public static String iOSBundleId = "com.yit.evrit";
    public static String iOSAppInstallationPath = selectAppToInstallChoiceBox.getValue();


    public static String email = "Automation@Gini-Apps.com";
    public static String password = "automation";
}