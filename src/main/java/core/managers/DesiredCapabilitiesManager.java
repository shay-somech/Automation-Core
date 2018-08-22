package core.managers;

import core.managers.drivers.DriverServiceBuilder;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.UI.MainUIRunner.shouldInstallAppChoiceBox;
import static core.constants.AppParams.*;
import static core.managers.drivers.AndroidDriverManager.isAndroid;
import static core.managers.drivers.IOSDriverManager.isIOS;

public class DesiredCapabilitiesManager {

    public static DesiredCapabilities setCapabilities() {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability("deviceName", "DEVICE");
        dc.setCapability("instrumentApp", true);
//        dc.setCapability("instrumentApp", Boolean.parseBoolean(instrumentAppChoiceBox.getValue()));
//        dc.setCapability("noReset", Boolean.parseBoolean(noResetChoiceBox.getValue()));
        dc.setCapability(MobileCapabilityType.UDID, DriverServiceBuilder.getDeviceID());

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

        if (isIOS) {
            dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, iOSBundleId);
        }

        if (isAndroid) {
            dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, androidAppPackage);
            dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, androidAppMainActivity);
        }

        return dc;
    }
}
