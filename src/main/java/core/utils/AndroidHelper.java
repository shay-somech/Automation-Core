package core.utils;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import io.appium.java_client.android.Activity;

import java.io.File;
import java.util.ArrayList;

import static core.baseclasses.ElementFinder.getElementByPartialText;
import static core.constants.ZoneType.NATIVE;

public class AndroidHelper {

    private static AndroidHelper instance;

    public static AndroidHelper getInstance() {
        if (instance == null)
            instance = new AndroidHelper();
        return instance;
    }

    public static ArrayList<String> getAndroidDevices() {
        MyLogger.logSys("Checking for available devices");
        ArrayList<String> availableDevices = new ArrayList<>();
        ArrayList connectedDevices = ADBHelper.getConnectedDevices();
        for (Object connectedDevice : connectedDevices) {
            availableDevices.add(connectedDevice.toString());
        }
        if (availableDevices.size() == 0)
            throw new RuntimeException("Not a single device is available for testing at this time");
        return availableDevices;
    }

    String getAndroidCurrentActivity() {
        MyLogger.logSys("Getting Android Current Activity");
        String currentActivity = null;
        try {
            currentActivity = DriverManager.getAndroidDriver().currentActivity();
        } catch (RuntimeException e) {
            MyLogger.logSys("Cannot get Android Current Activity");
        }
        return currentActivity;
    }

    void openAndroidNotificationCenter() {
        MyLogger.logSys("Opening Android Notification Center");
        try {
            DriverManager.getAndroidDriver().openNotifications();
        } catch (RuntimeException e) {
            MyLogger.logSys("Cannot open Android Notification Center");
        }
    }

    void startAndroidActivity(String appPackage, String appMainActivity) {
        MyLogger.logSys("Starting Android App Activity");
        DriverManager.getAndroidDriver().startActivity(new Activity(appPackage, appMainActivity));
        ;
    }

    void clickAndroidBackButton() {
        MyLogger.logSys("Clicking on the Android device back button");
        try {
            DriverManager.getDriver().navigate().back();
        } catch (RuntimeException e) {
            MyLogger.logSys("Cannot click on Android device back button : " + e.getMessage());
        }
    }

    void hideAndroidKeyboard() {
        MyLogger.logSys("Closing Android Keyboard");
        try {
            DriverManager.getAndroidDriver().hideKeyboard();
        } catch (RuntimeException e) {
            MyLogger.logSys("Cannot hide Android Keyboard");
        }
    }

    void launchAndroidSettings() {
        MyLogger.logSys("Launching Android Settings");
        DriverManager.getAndroidDriver().startActivity(new Activity("com.android.settings", ".Settings"));
    }

    public void completeActionWithIntent(String appName) {
        MyLogger.logSys("Selecting to complete action with " + appName);
        if (completeActionWithIntentDisplayed()) {
            getElementByPartialText(appName).findAndClick();
        }
    }

    private boolean completeActionWithIntentDisplayed() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        return getElementByPartialText("פתח באמצעות").findAndReturn().isExistAndDisplayed() || getElementByPartialText("Open with").findAndReturn().isExistAndDisplayed();
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