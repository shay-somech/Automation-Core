package core.managers.drivers;

import core.managers.MyLogger;
import core.managers.baseclasses.DeviceManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import static core.managers.baseclasses.DeviceManager.setPlatform;

public class DriverManager {

    private static AppiumDriver driver;
    private static String currentSessionId = "";

    private DriverManager() {
        MyLogger.logSys("DriverManager initialized");

        String platform = System.getProperty("Platform","Android");

        if (platform.equalsIgnoreCase("Android")) {
            setPlatform(DeviceManager.Platform.Android);
        } else {
            setPlatform(DeviceManager.Platform.iOS);
        }

        currentSessionId = driver.getSessionId().toString();
    }

    public static AppiumDriver getDriver() {
        if (driver == null || isDriverExpired()) {
            new DriverManager();
        }
        return driver;
    }

    static void setDriver(AppiumDriver driver) {
        DriverManager.driver = driver;
    }

    public static boolean isDriverExpired() {
        return isDriverExpired(currentSessionId);
    }

    public static boolean isDriverExpired(String sessionId) {
        return driver == null || driver.getSessionId() == null || !driver.getSessionId().toString().equals(sessionId);
    }

    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) getDriver();
    }

    public static IOSDriver getIosDriver() {
        return (IOSDriver) getDriver();
    }
}
