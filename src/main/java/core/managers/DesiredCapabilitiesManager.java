package core.managers;

import core.UI.Controller;
import core.managers.drivers.DriverServiceBuilder;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.constants.AppParams.*;
import static core.managers.drivers.AndroidDriverManager.isAndroid;
import static core.managers.drivers.IOSDriverManager.isIOS;
import static core.utils.AndroidHelper.getAndroidAppInstallationPath;
import static core.utils.IOSHelper.getIOSAppInstallationPath;

public class DesiredCapabilitiesManager {

    private static DesiredCapabilities dc = new DesiredCapabilities();

    public static DesiredCapabilities setCapabilities() {

        dc.setCapability("deviceName", DriverServiceBuilder.getDeviceID());
        dc.setCapability(MobileCapabilityType.UDID, DriverServiceBuilder.getDeviceID());

        if (JenkinsManager.getInstance().isBuildingFromJenkins()) {

            dc.setCapability("noReset", true);

            if (isAndroid) {
                dc.setCapability(MobileCapabilityType.APP, getAndroidAppInstallationPath());
            } else {
                dc.setCapability(MobileCapabilityType.APP, getIOSAppInstallationPath());
            }

        } else {
            dc.setCapability("noReset", Controller.resetApp);

            if (Controller.installApp) {
                if (isAndroid) {
                    dc.setCapability(MobileCapabilityType.APP, getAndroidAppInstallationPath());
                } else {
                    dc.setCapability(MobileCapabilityType.APP, getIOSAppInstallationPath());
                }
            }
        }

        if (isIOS) {
            dc.setCapability("automationName", "XCUITest");
            dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, iOSBundleId);
        }

        if (isAndroid) {
            dc.setCapability("automationName", "UiAutomator2");
            dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, androidAppPackage);
            dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, androidAppMainActivity);
        }

        return dc;
    }
}
