package core.managers.drivers;

import core.constants.AppiumServerArgs;
import core.managers.JenkinsManager;
import core.utils.Log;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import static core.managers.JenkinsManager.JenkinsProperty.JENKINS_INSTANCE;
import static core.managers.JenkinsManager.JenkinsProperty.PLATFORM;

public class DriverServiceBuilder {

    private static String OS;
    private static AppiumDriverLocalService service;
    private static String unlockPackage = "io.appium.unlock";

    private static String getOS() {
        if (OS == null) OS = System.getProperty("os.name");
        Log.info("Running Automation on " + OS);
        return OS;
    }

    private static boolean isWindows() {
        return getOS().startsWith("Windows");
    }

    private static boolean isMac() {
        return getOS().startsWith("Mac");
    }

    public static AppiumDriverLocalService createAppiumService() {
        int port = 4723;
        String ipAddress = "127.0.0.1";

        if (service == null) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingPort(port)
                    .withIPAddress(ipAddress)
                    .withArgument(AppiumServerArgs.RELAXED_SECURITY)
                    .withArgument(AppiumServerArgs.LOG_TIMESTAMP)
                    .withArgument(AppiumServerArgs.LOG_LEVEL, AppiumServerArgs.WARNING_ERROR)
                    .withArgument(AppiumServerArgs.TEMP_DIRECTORY, null));
        }

        return service;
    }

    public static void createJenkinsDriver() {
        createAppiumService().start();

        if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {

            if (JenkinsManager.getInstance().getJenkinsSelection(PLATFORM).equals("Android")) {
                DriverManager.setDriver("Android", service);
            } else {
                DriverManager.setDriver("iOS", service);
            }
        } else {
            throw new RuntimeException("Cannot build from Jenkins, please make sure that the Running service is indeed from Jenkins");
        }
    }

    /**
     * Build manually with UI Configurations
     */
    public static void createDriver(String platform) {
        DriverManager.setDriver(platform, service);
    }

    public static void killDriver() {
        if (DriverManager.getDriver() != null) {
            Log.info("Killing Driver");
            service.stop();
        } else Log.info("Driver is not initialized, nothing to kill");
    }
}
