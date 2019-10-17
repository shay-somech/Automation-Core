package core.managers;

import core.utils.Log;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static core.UI.controller.tab.Tab1Controller.uiSelection;
import static core.UI.controller.tab.Tab2Controller.isInstallApp;
import static core.UI.controller.tab.Tab2Controller.isParallelRun;
import static core.managers.drivers.DriverManager.isAndroid;
import static core.utils.AndroidHelper.getAndroidAppInstallationPath;
import static core.utils.AppParams.*;
import static core.utils.IOSHelper.getIOSAppInstallationPath;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.IOSMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

public class DesiredCapabilitiesManager {

    private DesiredCapabilities caps;
    private HashMap<String, String> customFindModules = new HashMap<>();

    public DesiredCapabilitiesManager() {
        caps = new DesiredCapabilities();

        if (isAndroid) {
            caps = setAndroidCaps();
        } else {
            caps = setIOSCaps();
        }
    }

    private DesiredCapabilities setAndroidCaps() {
        caps.setCapability(DEVICE_NAME, DeviceManager.getDeviceID());
        caps.setCapability(UDID, DeviceManager.getDeviceID());

        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            caps.setCapability(NO_RESET, true);
//            caps.setCapability(APP, getAndroidAppInstallationPath());

        } else {
            Log.info("NoReset Caps == " + uiSelection.get("NoReset"));
            caps.setCapability(NO_RESET, uiSelection.get("NoReset"));

            if (isInstallApp) {
                caps.setCapability(APP, getAndroidAppInstallationPath());
            }

            if (isParallelRun) {
                caps.setCapability(UDID, "");
                caps.setCapability(SYSTEM_PORT, 8200);
            }
        }

        caps.setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
        caps.setCapability(APP_PACKAGE, getAndroidAppPackage());
        caps.setCapability(APP_ACTIVITY, getAndroidAppMainActivity());
        caps.setCapability(APP_WAIT_ACTIVITY, getAndroidAppMainActivity());
        caps.setCapability(APP_WAIT_PACKAGE, getAndroidAppPackage());
        caps.setCapability(AUTO_GRANT_PERMISSIONS, true);
        caps.setCapability("autoLaunch", false);

        /**
         Skip the installation of io.appium.settings app and the UIAutomator 2 server.
         */
        caps.setCapability("skipDeviceInitialization", true);
        caps.setCapability("skipServerInstallation", true);

        /**
         * AI Locator Based Capability - Test.ai
         * https://github.com/testdotai/appium-classifier-plugin
         */
        customFindModules.put("ai", "test-ai-classifier");
        caps.setCapability("customFindModules", customFindModules);

        return caps;
    }

    private DesiredCapabilities setIOSCaps() {
        caps.setCapability(DEVICE_NAME, DeviceManager.getDeviceID());
        caps.setCapability(UDID, DeviceManager.getDeviceID());

        if (JenkinsManager.getInstance().getJenkinsInstance()) {
            caps.setCapability(NO_RESET, true);
//            caps.setCapability(APP, getIOSAppInstallationPath());

        } else {
            caps.setCapability(NO_RESET, uiSelection.get("NoReset"));

            if (isInstallApp) {
                caps.setCapability(APP, getIOSAppInstallationPath());
            }

            if (isParallelRun) {
                caps.setCapability(UDID, "");
                caps.setCapability(WDA_LOCAL_PORT, 8200);
            }
        }
        caps.setCapability(AUTOMATION_NAME, IOS_XCUI_TEST);
        caps.setCapability(USE_PREBUILT_WDA, true);
        caps.setCapability(WDA_LAUNCH_TIMEOUT, 60000);
        caps.setCapability(WDA_CONNECTION_TIMEOUT, 60000);
        caps.setCapability(WDA_STARTUP_RETRIES, 3);
        caps.setCapability(WDA_STARTUP_RETRY_INTERVAL, 20000);
        caps.setCapability(LAUNCH_TIMEOUT, 60000);
        caps.setCapability(BUNDLE_ID, getIOSBundleId());
        caps.setCapability("autoLaunch", false);

        /**
         * AI Locator Based Capability - Test.ai
         * https://github.com/testdotai/appium-classifier-plugin
         */
        customFindModules.put("ai", "test-ai-classifier");
        caps.setCapability("customFindModules", customFindModules);

        return caps;
    }

    public DesiredCapabilities getDesiredCapabilities() {
        return caps;
    }
}
