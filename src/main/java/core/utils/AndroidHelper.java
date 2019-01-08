package core.utils;

import core.UI.controller.tab.Tab1Controller;
import core.managers.JenkinsManager;
import core.managers.drivers.DriverManager;
import io.appium.java_client.android.Activity;

import java.io.File;
import java.util.ArrayList;

import static core.constants.FindByLocator.PARTIAL_TEXT;
import static core.constants.ZoneType.NATIVE;
import static core.managers.JenkinsManager.JenkinsProperty.JENKINS_INSTANCE;
import static core.utils.ADBHelper.*;
import static core.utils.ElementWrapper.waitForElementToAppear;

public class AndroidHelper {

    public static ArrayList<String> getAndroidDevices() {
        Log.info("Checking for available devices");
        ArrayList<String> availableDevices = new ArrayList<>();
        ArrayList<String> ADBConnectedDevices = ADBHelper.getConnectedDevices();

        for (String connectedDevice : ADBConnectedDevices) {
            availableDevices.add(connectedDevice);
            Log.info("Found Android device: " + connectedDevice);
        }

        if (availableDevices.size() == 0) {
            Log.info("Not a single device is available for testing at this time");
        }

        return availableDevices;
    }

    public static ArrayList<String> getAndroidDeviceWithDetails() {
        Log.info("Checking for available Android devices");
        ArrayList<String> availableDevices = new ArrayList<>();

        for (String connectedAndroidDevice : getAndroidDevices()) {
            String androidDevice = getDeviceModel(connectedAndroidDevice) + " " + getDeviceManufacturer(connectedAndroidDevice) + " " + getAndroidVersionAsString(connectedAndroidDevice) + " || " + connectedAndroidDevice;
            availableDevices.add(androidDevice);
        }

        return availableDevices;
    }

    public static String getAndroidAppInstallationPath() {
        String apkAbsolutePath = null;

        if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
            for (File apk : getAvailableAPKs("/src/main/resources/")) {
                apkAbsolutePath = apk.getAbsolutePath();
            }
            return apkAbsolutePath;
        } else {
            return Tab1Controller.app;
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
    }

    void clickAndroidBackButton() {
        Log.info("Clicking on the Android device back button");
        try {
            DriverManager.getAndroidDriver().navigate().back();
        } catch (RuntimeException e) {
            Log.info("Cannot click on Android device back button : " + e.getMessage());
        }
    }

    void launchAndroidSettings() {
        Log.info("Launching Android Settings");
        DriverManager.getAndroidDriver().startActivity(new Activity("com.androidRadioButton.settings", ".Settings"));
    }

    public void completeActionWithIntent(String appName) {
        Log.info("Selecting to complete action with " + appName);
        if (completeActionWithIntentDisplayed()) {
            waitForElementToAppear(PARTIAL_TEXT, appName, 5).click();
        }
    }

    private boolean completeActionWithIntentDisplayed() {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        return waitForElementToAppear(PARTIAL_TEXT, "פתח באמצעות", 3).isDisplayed() || waitForElementToAppear(PARTIAL_TEXT, "Open with", 3).isDisplayed();
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