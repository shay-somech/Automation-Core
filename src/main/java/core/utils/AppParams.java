package core.utils;

public class AppParams {

    private static String androidAppPackage;
    private static String androidAppMainActivity;
    private static String iOSBundleId;

    public static String setAndroidAppPackage(String appPackage) {
        return androidAppPackage = appPackage;
    }

    public static String setAndroidAppMainActivity(String appMainActivity) {
        return androidAppMainActivity = appMainActivity;
    }

    public static String setIOSBundleId(String bundleId) {
        return iOSBundleId = bundleId;
    }


    public static String getAndroidAppPackage() {
        return androidAppPackage;
    }

    public static String getAndroidAppMainActivity() {
        return androidAppMainActivity;
    }

    public static String getIOSBundleId() {
        return iOSBundleId;
    }
}