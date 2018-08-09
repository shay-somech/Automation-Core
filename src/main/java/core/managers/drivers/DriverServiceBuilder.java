package core.managers.drivers;

import core.managers.MyLogger;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import javafx.scene.control.ChoiceBox;

import static core.UI.MainUIRunner.selectDeviceChoiceBox;
import static core.managers.AutomationLauncher.isBuildingFromJenkins;

public class DriverServiceBuilder {

    private static String OS;
    private static AppiumDriverLocalService service;
    private static String deviceID;
    private static String unlockPackage = "io.appium.unlock";

    private static String getOS() {
        if (OS == null) OS = System.getProperty("os.name");
        MyLogger.logSys("Running Automation on " + OS);
        return OS;
    }

    private static boolean isWindows() {
        return getOS().startsWith("Windows");
    }

    private static boolean isMac() {
        return getOS().startsWith("Mac");
    }

    static String getDeviceID() {
        if (deviceID == null) {
            deviceID = selectDeviceChoiceBox.getValue().substring(selectDeviceChoiceBox.getValue().indexOf("|| ") + 3);
        }
        return deviceID;
    }

    private static AppiumDriverLocalService createAppiumService() {

        if (service == null) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "warn")
                    .withArgument(GeneralServerFlag.TEMP_DIRECTORY, null));
        }

        return service;
    }

    public static void createDriver(ChoiceBox<String> platform) {
        createAppiumService().start();

        if (isBuildingFromJenkins()) {

            /** Build with Jenkins parameterized */
            String jenkinsPlatformProperty = System.getProperty("Platform", "Android");
            if (jenkinsPlatformProperty.equals("Android")) {
                AndroidDriverManager.getInstance().startDriver(service);
            } else {
                IOSDriverManager.getInstance().startDriver(service);
            }

        } else {

            /** Build manually with UI Configurations */
            if (platform.getValue().equals("Android")) {
                AndroidDriverManager.getInstance().startDriver(service);
            } else if (platform.getValue().equals("iOS")) {
                IOSDriverManager.getInstance().startDriver(service);
            }
        }
    }

    public static void killDriver() {
        if (DriverManager.driver != null) {
            MyLogger.logSys("Killing Driver");
            service.stop();
        } else MyLogger.logSys("Driver is not initialized, nothing to kill");
    }
}
