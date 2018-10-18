package core.managers.drivers;

import core.utils.Log;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.SessionNotCreatedException;

import static core.managers.DesiredCapabilitiesManager.setCapabilities;

public class IOSDriverManager {

    public static boolean isIOS = false;
    private static IOSDriverManager instance;

    public static IOSDriverManager getInstance() {
        if (instance == null)
            instance = new IOSDriverManager();
        return instance;
    }

    void startDriver(AppiumDriverLocalService service) {
        try {
            isIOS = true;
            Log.info("Starting iOS driver");
            DriverManager.setDriver(new IOSDriver<>(service, setCapabilities()));

        } catch (SessionNotCreatedException e) {
            Log.info("Failed to start iOS driver");

        }
    }
}
