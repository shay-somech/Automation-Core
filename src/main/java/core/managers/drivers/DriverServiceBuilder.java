package core.managers.drivers;

import core.constants.AppiumServerArgs;
import core.managers.MyLogger;
import core.utils.ADBHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DriverServiceBuilder {

    private static String OS;
    private static HashMap<String, URL> hosts;
    private static DriverService service;
    private static String deviceID;
    private static String nodeJS = "/usr/local/bin/node";
    private static String appiumJS = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/appium.js";
    private static String unlockPackage = "io.appium.unlock";


    private static String getOS() {
        if (OS == null) OS = System.getenv("os.name");
        return OS;
    }

    public static boolean isWindows() {
        return getOS().startsWith("Windows");
    }

    private static boolean isMac() {
        return getOS().startsWith("Mac");
    }

    private static ArrayList<String> getAvailableDevices() {
        MyLogger.logSys("Checking for available devices");
        ArrayList<String> availableDevices = new ArrayList<>();
        ArrayList connectedDevices = ADBHelper.getConnectedDevices();
        for (Object connectedDevice : connectedDevices) {
            deviceID = connectedDevice.toString();
            availableDevices.add(deviceID);
        }
        if (availableDevices.size() == 0)
            throw new RuntimeException("Not a single device is available for testing at this time");
        return availableDevices;
    }

    private static DesiredCapabilities getCaps(String deviceID) {
        MyLogger.logSys("Creating driver caps for device: " + deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("instrumentApp", true);
        caps.setCapability("deviceName", deviceID);
        caps.setCapability("platformName", "Android");
        return caps;
    }

    private static URL host(String deviceID) throws MalformedURLException {
        if (hosts == null) {
            hosts = new HashMap<>();
            hosts.put(deviceID, new URL("http://127.0.0.1:4723/wd/hub"));
            //if parallel - copy and change the port to 4724
        }
        return hosts.get(deviceID);
    }

    private static DriverService createService() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodeJS))
                .withAppiumJS(new File(appiumJS))
                .withIPAddress(host(deviceID).toString().split(":")[1].replace("//", ""))
                .usingPort(Integer.parseInt(host(deviceID).toString().split(":")[2].replace("/wd/hub", "")))
                .withArgument(AppiumServerArgs.TIMEOUT, "120")
                .withArgument(AppiumServerArgs.LOG_LEVEL, "debug")
                .build();
        return service;
    }

    static void createDriver() {
        ArrayList<String> devices = getAvailableDevices();
        for (String device : devices) {
            try {
                deviceID = device;
                MyLogger.logSys("Trying to create new Driver for device: " + device);
                createService().start();

                DriverManager.driver = new AndroidDriver(host(device), getCaps(device));

//                //Build with Jenkins parameterized
//                String platform = System.getProperty("Platform", "Android");
//                if (platform.equals("Android")) {
//                } else {
//                    DriverManager.driver = new IOSDriver(host(device), getCaps(device));
//                }

            } catch (Exception e) {
                e.printStackTrace();
                //Ignore and try next device
            }
        }
    }

    public static void killDriver() {
        if (DriverManager.driver != null) {
            MyLogger.logSys("Killing Driver");
            DriverManager.getDriver().quit();
            service.stop();
        } else MyLogger.logSys("Driver is not initialized, nothing to kill");
    }
}
