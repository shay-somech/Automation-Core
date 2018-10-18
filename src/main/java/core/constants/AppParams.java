package core.constants;

public class AppParams {

    public static String androidAppPackage;
    public static String androidAppMainActivity;
    public static String iOSBundleId = "com.yit.evrit";
    public static final String email = "Automation@Gini-Apps.com";
    public static final String password = "automation";


    public void setAndroidAppPackage(String androidAppPackage) {
        AppParams.androidAppPackage = androidAppPackage;
    }

    public void setAndroidAppMainActivity(String androidAppMainActivity) {
        AppParams.androidAppMainActivity = androidAppMainActivity;
    }

    public void setiOSBundleId(String iOSBundleId) {
        AppParams.iOSBundleId = iOSBundleId;
    }
}