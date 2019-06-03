package core.managers.drivers;

import core.constants.PlatformType;
import core.utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static core.constants.PlatformType.ANDROID;
import static core.constants.PlatformType.IOS;
import static core.managers.DesiredCapabilitiesManager.setCapabilities;

public class DriverManager {

    public static WebDriverWait wait;
    public static boolean isAndroid = false;
    public static boolean isIOS = false;
    private static AppiumDriver driver;

    static void setDriver(PlatformType platform, AppiumDriverLocalService service) {
        try {
            switch (platform) {
                case ANDROID:
                    Log.info("Starting Android driver");
                    isAndroid = true;
                    driver = new AndroidDriver<>(service, setCapabilities(ANDROID));
                    break;

                case IOS:
                    Log.info("Starting iOS driver");
                    isIOS = true;
                    driver = new IOSDriver<>(service, setCapabilities(IOS));
                    break;
            }

            Log.info("Driver initialized");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        } catch (SessionNotCreatedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start Appium driver");
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null || isDriverExpired())
            throw new RuntimeException("Driver not Initialized or Session Expired!");
        return driver;
    }

    private static String getCurrentSessionId() {
        return driver.getSessionId().toString();
    }

    private static boolean isDriverExpired() {
        return driver == null || driver.getSessionId() == null || !driver.getSessionId().toString().equals(getCurrentSessionId());
    }

    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) getDriver();
    }

    public static IOSDriver getIOSDriver() {
        return (IOSDriver) getDriver();
    }

    public static WebDriverWait webDriverWait(int timeout) {
        wait = new WebDriverWait(driver, timeout);
        return wait;
    }
}
