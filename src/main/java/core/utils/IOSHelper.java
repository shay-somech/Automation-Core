package core.utils;

import core.UI.controller.tab.Tab2Controller;
import core.managers.JenkinsManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static core.managers.JenkinsManager.JenkinsProperty.JENKINS_INSTANCE;

public class IOSHelper {

    public static ArrayList<String> getIOSDevices() {
        ArrayList<String> devices = new ArrayList<>();
        try {
            String line;
            Process getConnectedDevices = Runtime.getRuntime().exec("idevice_id -l");
            BufferedReader input = new BufferedReader(new InputStreamReader(getConnectedDevices.getInputStream()));
            while ((line = input.readLine()) != null) {
                Log.info("Found iOS device: " + line);
                if (line.length() > 0) {
                    devices.add(line);
                }
            }
            input.close();
        } catch (IOException e) {
            Log.info("Not a single iOS device is available for testing at this time");
        }

        if (devices.size() == 0)
            Log.info("Not a single iOS device is available for testing at this time");
        return devices;
    }

    public static ArrayList<String> getIOSDevicesWithDetails() {
        ArrayList<String> availableDevices = new ArrayList<>();

        Log.info("Checking for available iOS devices");
        for (Object connectedIOSDevice : getIOSDevices()) {
            String iOSDevice = getDeviceName(connectedIOSDevice.toString()) + " || " + connectedIOSDevice.toString();
            availableDevices.add(iOSDevice);
        }
        return availableDevices;
    }

    public static String getIOSAppInstallationPath() {
        String ipaAbsolutePath = null;

        if ((boolean) JenkinsManager.getInstance().getJenkinsSelection(JENKINS_INSTANCE)) {
            for (File apk : getAvailableIPAs("/src/main/resources/")) {
                ipaAbsolutePath = apk.getAbsolutePath();
            }
            return ipaAbsolutePath;
        } else {
            return Tab2Controller.app;
        }
    }

    private static String getDeviceName(String udid) {
        try {
            String line;
            Process getDeviceName = Runtime.getRuntime().exec("idevicename -u " + udid);
            BufferedReader input = new BufferedReader(new InputStreamReader(getDeviceName.getInputStream()));
            if ((line = input.readLine()) != null) {
                return line;
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can't get Device Name");
    }

    public static String getDeviceVersion(String udid) {
        try {
            String line;
            Process getDeviceVersion = Runtime.getRuntime().exec("ideviceinfo -u " + udid + "| grep ProductVersion");
            BufferedReader input = new BufferedReader(new InputStreamReader(getDeviceVersion.getInputStream()));
            if ((line = input.readLine()) != null) {
                return line;
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can't get Device Version");
    }

    public static ArrayList<File> getAvailableIPAs(String fileDirectoryPath) {
        String directoryPath = System.getProperty("user.dir") + fileDirectoryPath;
        File directory = new File(directoryPath);
        ArrayList<File> fileList = new ArrayList<>();

        // get all the files from a directory
        File[] files = directory.listFiles();

        if (files != null) {
            for (File object : files) {
                if (object.isFile() && object.getAbsolutePath().contains(".ipa")) {
                    fileList.add(object);
                }
            }
        }
        return fileList;
    }
}
