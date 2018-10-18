package core.managers.drivers;

import core.utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    static AppiumDriver driver;
    private static String currentSessionId = "";

    private DriverManager() {
        Log.info("DriverManager initialized");

        currentSessionId = getCurrentSessionId();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

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
