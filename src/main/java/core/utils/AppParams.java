package core.utils;

public class AppParams {

    private static String appPackage;
    private static String appMainActivity;
    private static String bundleId;

    public AppParams(String androidAppPackage, String androidAppMainActivity, String iOSBundleId) {
        appPackage = androidAppPackage;
        appMainActivity = androidAppMainActivity;
        bundleId = iOSBundleId;
    }

    public static String getAndroidAppPackage() {
        return appPackage;
    }

    public static String getAndroidAppMainActivity() {
        return appMainActivity;
    }

    public static String getIOSBundleId() {
        return bundleId;
    }
}