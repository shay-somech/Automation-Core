package core.managers;

import core.constants.PlatformType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.UI.controller.tab.Tab1Controller.isNoReset;
import static core.UI.controller.tab.Tab2Controller.isInstallApp;
import static core.UI.controller.tab.Tab2Controller.isParallelRun;
import static core.managers.JenkinsManager.JenkinsProperty.JENKINS_INSTANCE;
import static core.managers.JenkinsManager.JenkinsProperty.NO_RESET_PROPERTY;
import static core.utils.AndroidHelper.getAndroidAppInstallationPath;
import static core.utils.AppParams.*;
import static core.utils.IOSHelper.getIOSAppInstallationPath;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.IOSMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

public class DesiredCapabilitiesManager {

    private static DesiredCapabilities caps = new DesiredCapabilities();

    public static DesiredCapabilities setCapabilities(PlatformType platform) {

        caps.setCapability(DEVICE_NAME, DeviceManager.getDeviceID());
        caps.setCapability(UDID, DeviceManager.getDeviceID());

        switch (platform) {
            case ANDROID:
                if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
                    caps.setCapability(NO_RESET, JenkinsManager.getInstance().getJenkinsSelection(NO_RESET_PROPERTY));
                    caps.setCapability(APP, getAndroidAppInstallationPath());

                } else {
                    caps.setCapability(NO_RESET, isNoReset);

                    if (isInstallApp) {
                        caps.setCapability(APP, getAndroidAppInstallationPath());
                    }

                    if (isParallelRun) {
                        caps.setCapability(UDID, DeviceManager.getSecondDeviceID());
                        caps.setCapability(SYSTEM_PORT, 8200);
                    }
                }

                caps.setCapability(AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(APP_PACKAGE, getAndroidAppPackage());
                caps.setCapability(APP_ACTIVITY, getAndroidAppMainActivity());
                caps.setCapability(APP_WAIT_ACTIVITY, getAndroidAppMainActivity());
                caps.setCapability(APP_WAIT_PACKAGE, getAndroidAppPackage());
                caps.setCapability(AUTO_GRANT_PERMISSIONS, true);
                caps.setCapability("skipDeviceInitialization", true);

                break;

            case IOS:
                if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
                    caps.setCapability(NO_RESET, JenkinsManager.getInstance().getJenkinsSelection(NO_RESET_PROPERTY));
                    caps.setCapability(APP, getIOSAppInstallationPath());

                } else {
                    caps.setCapability(NO_RESET, isNoReset);

                    if (isInstallApp) {
                        caps.setCapability(APP, getIOSAppInstallationPath());
                    }

                    if (isParallelRun) {
                        caps.setCapability(UDID, DeviceManager.getSecondDeviceID());
                        caps.setCapability(WDA_LOCAL_PORT, 8200);
                    }
                }

                caps.setCapability(AUTOMATION_NAME, "XCUITest");
                caps.setCapability(USE_PREBUILT_WDA, true);
                caps.setCapability(WDA_LAUNCH_TIMEOUT, 60000);
                caps.setCapability(WDA_CONNECTION_TIMEOUT, 60000);
                caps.setCapability(WDA_STARTUP_RETRIES, 3);
                caps.setCapability(WDA_STARTUP_RETRY_INTERVAL, 20000);
                caps.setCapability(LAUNCH_TIMEOUT, 60000);
                caps.setCapability(BUNDLE_ID, getIOSBundleId());

                break;
        }

        return caps;
    }
}
