package core.managers.drivers;

import core.managers.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {

    static AppiumDriver driver;
    private static String currentSessionId = "";

    private DriverManager() {
        MyLogger.logSys("DriverManager initialized");

        currentSessionId = getCurrentSessionId();

    }

    public static AppiumDriver getDriver() {
        if (driver == null || isDriverExpired()) {
            new DriverManager();
        }
        return driver;
    }

    private static String getCurrentSessionId() {
        return driver.getSessionId().toString();
    }

    static void setDriver(AppiumDriver driver) {
        DriverManager.driver = driver;
    }

    private static boolean isDriverExpired() {
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
