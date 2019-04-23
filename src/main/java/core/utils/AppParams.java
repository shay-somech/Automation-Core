package core.utils;

public class AppParams {

    private static String androidAppPackage;
    private static String androidAppMainActivity;
    private static String iOSBundleId;

    public AppParams(String androidAppPackage, String androidAppMainActivity, String iOSBundleId) {
        this.androidAppPackage = androidAppPackage;
        this.androidAppMainActivity = androidAppMainActivity;
        this.iOSBundleId = iOSBundleId;
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