package core.managers.drivers;

import core.constants.AppiumServerArgs;
import core.constants.PlatformType;
import core.managers.JenkinsManager;
import core.utils.Log;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import static core.constants.PlatformType.ANDROID;
import static core.constants.PlatformType.IOS;

public class DriverServiceBuilder {

    private static AppiumDriverLocalService service;

    public static AppiumDriverLocalService createAppiumService() {
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

        return service;
    }

    public static void createJenkinsDriver() {
        startAppiumServer();

        if (JenkinsManager.getInstance().getJenkinsInstance()) {

            switch (JenkinsManager.getInstance().getJenkinsSelectedPlatform()) {
                case ANDROID:
                    DriverManager.setDriver(ANDROID, service);
                    break;

                case IOS:
                    DriverManager.setDriver(IOS, service);
                    break;
            }
        }
    }

    /**
     * Build manually with UI Configurations
     */
    public static void createDriver(PlatformType platform) {
        DriverManager.setDriver(platform, service);
    }

    public static void startAppiumServer() {
        createAppiumService().start();
    }

    public static void stopAppiumServer() {
        if (DriverManager.getDriver() != null) {
            Log.info("Stopping Appium Server");
            service.stop();
        } else Log.info("Appium Server not initialized, nothing to stop");
    }
}
