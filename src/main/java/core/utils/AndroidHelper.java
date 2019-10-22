package core.utils;

import core.utils.Log.TextColor;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

import java.io.File;
import java.util.ArrayList;

import static core.UI.controller.MainController.UiSelections.APP;
import static core.UI.controller.MainController.uiSelection;
import static core.utils.ADBHelper.*;

public class AndroidHelper {

    private AndroidDriver driver;

    public AndroidHelper() {
    }

    public AndroidHelper(AndroidDriver driver) {
        this.driver = driver;
    }

    public ArrayList<String> getAndroidDevices() {
        Log.info("Checking for available devices");
        ArrayList<String> ADBConnectedDevices = ADBHelper.getConnectedDevices();
        ArrayList<String> availableDevices = new ArrayList<>();

        for (String connectedDevice : ADBConnectedDevices) {
            availableDevices.add(connectedDevice);
            Log.info("Found Android device: " + connectedDevice);
        }

        if (availableDevices.size() == 0) {
            Log.info(TextColor.ANSI_RED, "Not a single device is available for testing at this time");
        }

        return availableDevices;
    }

    public ArrayList<String> getAndroidDeviceWithDetails() {
        Log.info("Checking for available Android devices");
        ArrayList<String> availableDevices = new ArrayList<>();

        for (String connectedAndroidDevice : getAndroidDevices()) {
            String androidDevice = getDeviceModel(connectedAndroidDevice) + " "
                    + getDeviceManufacturer(connectedAndroidDevice) + " "
                    + getAndroidVersionAsString(connectedAndroidDevice) + " || "
                    + connectedAndroidDevice;
            availableDevices.add(androidDevice);
        }

        return availableDevices;
    }

    public String getAndroidAppInstallationPath() {
        String apkAbsolutePath = null;

        String app = (String) uiSelection.get(APP);
        if (app.isEmpty()) {
            for (File apk : getAvailableAPKs("/src/main/resources/")) {
                apkAbsolutePath = apk.getAbsolutePath();
            }
            return apkAbsolutePath;
        } else {
            return app;
        }
    }

    String getAndroidCurrentActivity() {
        Log.info("Getting Android Current Activity");
        return driver.currentActivity();
    }

    void openAndroidNotificationCenter() {
        driver.openNotifications();
    }

    void startAndroidActivity(String appPackage, String appMainActivity) {
        Log.info("Starting Android IOSSystemApp Activity");
        driver.startActivity(new Activity(appPackage, appMainActivity));
    }

    void clickAndroidBackButton() {
        Log.info("Clicking on the Android device back button");
        try {
            driver.navigate().back();
        } catch (RuntimeException e) {
            Log.info("Cannot click on Android device back button : " + e.getMessage());
        }
    }

    void launchAndroidSettings() {
        Log.info("Launching Android Settings");
        driver.startActivity(new Activity("com.androidRadioButton.settings", ".Settings"));
    }

    public ArrayList<File> getAvailableAPKs(String fileDirectoryPath) {
        String directoryPath = System.getProperty("user.dir") + fileDirectoryPath;
        File directory = new File(directoryPath);
        ArrayList<File> fileList = new ArrayList<>();

        // get all the files from a directory
        File[] files = directory.listFiles();

        if (files != null) {
            for (File object : files) {
                if (object.isFile() && object.getAbsolutePath().contains(".apk")) {
                    fileList.add(object);
                }
            }
        }
        return fileList;
    }

    /**
     * method to get network settings
     */
    void getNetworkConnection() {
        Log.info("Device connection : " + driver.getConnection());
    }

    /**
     * method to set network settings
     *
     * @param airplaneMode pass true to activate airplane mode else false
     * @param wifi         pass true to activate wifi mode else false
     * @param data         pass true to activate data mode else false
     */
    void setNetworkConnection(boolean airplaneMode, boolean wifi, boolean data) {

        long mode = 1L;

        if (wifi) {
            mode = 2L;
        } else if (data) {
            mode = 4L;
        } else if (airplaneMode) {
            mode = 3L;
        }

        ConnectionState connectionState = new ConnectionState(mode);
        driver.setConnection(connectionState);
        Log.info("Your current connection settings are : " + driver.getConnection());
    }
}