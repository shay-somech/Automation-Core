package core.managers.drivers;

import core.constants.AppiumServerArgs;
import core.constants.PlatformType;
import core.utils.Log;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import static core.constants.PlatformType.ANDROID;
import static core.constants.PlatformType.IOS;

public class DriverServiceBuilder {

    private static AppiumDriverLocalService service;

    public DriverServiceBuilder() {
        createAppiumService();
    }

    private void createAppiumService() {
        String ipAddress = "127.0.0.1";

        if (service == null) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withIPAddress(ipAddress)
                    .withArgument(AppiumServerArgs.RELAXED_SECURITY)
                    .withArgument(AppiumServerArgs.LOG_TIMESTAMP)
                    .withArgument(AppiumServerArgs.LOG_LEVEL, AppiumServerArgs.WARNING_ERROR)
                    .withArgument(AppiumServerArgs.TEMP_DIRECTORY, null));
        }
    }

    public void startAppiumServer() {
        service.start();
    }

    public static void stopAppiumServer() {
        if (DriverManager.getDriver() != null) {
            Log.info("Stopping Appium Server");
            service.stop();
        } else Log.info("Appium Server not initialized, nothing to stop");
    }

    /**
     * Build from Jenkins params
     * @param platformType is being fetched from Jenkins System property
     */
    public void createJenkinsDriver(PlatformType platformType) {
        switch (platformType) {
            case ANDROID:
                DriverManager.setDriver(ANDROID, service);
                break;

            case IOS:
                DriverManager.setDriver(IOS, service);
                break;
        }
    }

    /**
     * Build manually with UI Configurations
     */
    public void createDriver(PlatformType platform) {
        DriverManager.setDriver(platform, service);
    }
}
