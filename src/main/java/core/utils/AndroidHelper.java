package core.utils;

import core.managers.JenkinsManager;
import core.managers.drivers.DriverManager;
import io.appium.java_client.android.Activity;

import java.io.File;
import java.util.ArrayList;

import static core.UI.ComboBoxes.selectAppToInstallComboBox;
import static core.baseclasses.ElementFinder.findElementBy;
import static core.constants.FindByLocator.PARTIAL_TEXT;
import static core.constants.ZoneType.NATIVE;

public class AndroidHelper {

    private static AndroidHelper instance;

    public static AndroidHelper getInstance() {
        if (instance == null)
            instance = new AndroidHelper();
        return instance;
    }

    public static ArrayList<String> getAndroidDevices() {
        Log.info("Checking for available devices");
        ArrayList<String> availableDevices = new ArrayList<>();
        ArrayList ADBConnectedDevices = ADBHelper.getConnectedDevices();
        for (Object connectedDevice : ADBConnectedDevices) {
            availableDevices.add(connectedDevice.toString());
            Log.info("Found Android device :: " + connectedDevice);
        }
        if (availableDevices.size() == 0)
            throw new RuntimeException("Not a single device is available for testing at this time");
        return availableDevices;
    }

    public static String getAndroidAppInstallationPath() {
        String apkAbsolutePath = null;

        if (JenkinsManager.getInstance().isBuildingFromJenkins()) {
            for (File apk : getAvailableAPKs("/src/main/resources/")) {
                apkAbsolutePath = apk.getAbsolutePath();
            }
            return apkAbsolutePath;
        } else {
            return selectAppToInstallComboBox.getValue();
        }
    }

    String getAndroidCurrentActivity() {
        Log.info("Getting Android Current Activity");
        String currentActivity = null;
        try {
            currentActivity = DriverManager.getAndroidDriver().currentActivity();
        } catch (RuntimeException e) {
            Log.info("Cannot get Android Current Activity");
        }
        return currentActivity;
    }

    void openAndroidNotificationCenter() {
        Log.info("Opening Android Notification Center");
        try {
            DriverManager.getAndroidDriver().openNotifications();
        } catch (RuntimeException e) {
            Log.info("Cannot open Android Notification Center");
        }
    }

    void startAndroidActivity(String appPackage, String appMainActivity) {
        Log.info("Starting Android App Activity");
        DriverManager.getAndroidDriver().startActivity(new Activity(appPackage, appMainActivity));
        ;
    }

    void clickAndroidBackButton() {
        Log.info("Clicking on the Android device back button");
        try {
            DriverManager.getDriver().navigate().back();
        } catch (RuntimeException e) {
            Log.info("Cannot click on Android device back button : " + e.getMessage());
        }
    }

    void hideAndroidKeyboard() {
        Log.info("Closing Android Keyboard");
        try {
            DriverManager.getAndroidDriver().hideKeyboard();
        } catch (RuntimeException e) {
            Log.info("Cannot hide Android Keyboard");
        }
    }

    void launchAndroidSettings() {
        Log.info("Launching Android Settings");
        DriverManager.getAndroidDriver().startActivity(new Activity("com.android.settings", ".Settings"));
    }

    public void completeActionWithIntent(String appName) {
        Log.info("Selecting to complete action with " + appName);
        if (completeActionWithIntentDisplayed()) {
            findElementBy(PARTIAL_TEXT, appName).findAndClick();
        }
    }

    private boolean completeActionWithIntentDisplayed() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        return findElementBy(PARTIAL_TEXT, "פתח באמצעות").findAndReturn().isExistAndDisplayed() || findElementBy(PARTIAL_TEXT, "Open with").findAndReturn().isExistAndDisplayed();
    }

    public static ArrayList<File> getAvailableAPKs(String fileDirectoryPath) {
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
}