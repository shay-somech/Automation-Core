package core.managers;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.UI.MainUIRunner.*;
import static core.constants.AppInfo.*;
import static core.managers.drivers.AndroidDriverManager.isAndroid;
import static core.managers.drivers.DriverServiceBuilder.getDeviceID;
import static core.managers.drivers.IOSDriverManager.isIOS;

public class DesiredCapabilitiesManager {

    public static DesiredCapabilities setCapabilities() {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability("deviceName", getDeviceID());
        dc.setCapability("instrumentApp", Boolean.parseBoolean(instrumentAppChoiceBox.getValue()));
        dc.setCapability("noReset", Boolean.parseBoolean(noResetChoiceBox.getValue()));
        dc.setCapability("deviceName", getDeviceID());
        dc.setCapability(MobileCapabilityType.UDID, getDeviceID());

        if (isIOS)
            dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, iOSBundleId);

        if (isAndroid)
            dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, androidAppPackage);
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, androidAppMainActivity);

        if (JenkinsManager.getInstance().isBuildingFromJenkins()) {
            if (isAndroid) {
                dc.setCapability(MobileCapabilityType.APP, androidApp);
            } else {
                dc.setCapability(MobileCapabilityType.APP, iOSApp);
            }

        } else {
            if (Boolean.parseBoolean(shouldInstallAppChoiceBox.getValue())) {
                if (isAndroid) {
                    dc.setCapability(MobileCapabilityType.APP, androidApp);
                } else {
                    dc.setCapability(MobileCapabilityType.APP, iOSApp);
                }
            }
        }
        return dc;
    }
}
