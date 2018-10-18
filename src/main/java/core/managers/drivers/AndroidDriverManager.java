package core.managers.drivers;

import core.utils.Log;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.SessionNotCreatedException;

import static core.managers.DesiredCapabilitiesManager.setCapabilities;

public class AndroidDriverManager {

    public static boolean isAndroid = false;
    private static AndroidDriverManager instance;

    public static AndroidDriverManager getInstance() {
        if (instance == null)
            instance = new AndroidDriverManager();
        return instance;
    }

    void startDriver(AppiumDriverLocalService service) {
        try {
            isAndroid = true;
            Log.info("Starting Android driver");
            DriverManager.setDriver(new AndroidDriver<>(service, setCapabilities()));

        } catch (SessionNotCreatedException e) {
            Log.info("Failed to start Android driver");
        }
    }
}
