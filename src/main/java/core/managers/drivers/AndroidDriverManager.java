package core.managers.drivers;

import core.UI.UIExecutioner;
import core.managers.MyLogger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.UI.MainUIRunner.*;
import static core.constants.AppInfo.*;
import static core.managers.drivers.DriverServiceBuilder.getDeviceID;

public class AndroidDriverManager {

    public static boolean isAndroid = false;
    private static AndroidDriverManager instance;

    public static AndroidDriverManager getInstance() {
        if (instance == null)
            instance = new AndroidDriverManager();
        return instance;
    }

    private DesiredCapabilities setCapabilities() {
        DesiredCapabilities dc = UIExecutioner.dc;

        if (dc == null) {
            dc = new DesiredCapabilities();
        }

        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", getDeviceID());
        dc.setCapability("autoInstrument", Boolean.parseBoolean(autoInstrumentChoiceBox.getValue()));
        dc.setCapability("noReset", Boolean.parseBoolean(noResetChoiceBox.getValue()));
        dc.setCapability(MobileCapabilityType.UDID, getDeviceID());
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, androidAppPackage);
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, androidAppMainActivity);

        if (Boolean.parseBoolean(shouldInstallAppChoiceBox.getValue())) {
            dc.setCapability(MobileCapabilityType.APP, androidAppInstallationPath);
        }
        return dc;
    }

    void startDriver(AppiumDriverLocalService service) {
        try {
            MyLogger.logSys("Starting Android driver");
            DriverManager.setDriver(new AndroidDriver<>(service, setCapabilities()));
            isAndroid = true;

        } catch (SessionNotCreatedException e) {
            MyLogger.logSys("Failed to start Android driver");
        }
    }
}
