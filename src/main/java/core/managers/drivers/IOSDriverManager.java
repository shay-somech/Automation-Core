package core.managers.drivers;

import core.UI.UIExecutioner;
import core.managers.MyLogger;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.UI.MainUIRunner.*;
import static core.constants.AppInfo.iOSAppInstallationPath;
import static core.constants.AppInfo.iOSBundleId;
import static core.managers.drivers.DriverServiceBuilder.getDeviceID;

public class IOSDriverManager {

    public static boolean isIOS = false;
    private static IOSDriverManager instance;

    public static IOSDriverManager getInstance() {
        if (instance == null)
            instance = new IOSDriverManager();
        return instance;
    }

    private DesiredCapabilities setCapabilities() {
        DesiredCapabilities dc = UIExecutioner.dc;
        if (dc == null) {
            dc = new DesiredCapabilities();
        }

        dc.setCapability("instrumentApp", true);
        dc.setCapability("reportDirectory", "reports");
        dc.setCapability("reportFormat", "xml");
        dc.setCapability("autoInstrument", Boolean.parseBoolean(autoInstrumentChoiceBox.getValue()));
        dc.setCapability("noReset", Boolean.parseBoolean(noResetChoiceBox.getValue()));
        dc.setCapability("deviceName", getDeviceID());
        dc.setCapability(MobileCapabilityType.UDID, getDeviceID());
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, iOSBundleId);

        if (Boolean.parseBoolean(shouldInstallAppChoiceBox.getValue())) {
            dc.setCapability(MobileCapabilityType.APP, iOSAppInstallationPath);
        }
        return dc;
    }

    void startDriver(AppiumDriverLocalService service) {
        try {
            MyLogger.logSys("Starting iOS driver");
            DriverManager.setDriver(new IOSDriver<>(service, setCapabilities()));
            isIOS = true;

        } catch (SessionNotCreatedException e) {
            MyLogger.logSys("Failed to start iOS driver");

        }
    }
}
