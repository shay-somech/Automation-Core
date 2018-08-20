package core.utils;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static core.baseclasses.ElementFinder.getElementByAccessibilityLabel;

public class IOSHelper {

    private static IOSHelper instance;

    public static IOSHelper getInstance() {
        if (instance == null)
            instance = new IOSHelper();
        return instance;
    }

    public static ArrayList<String> getIOSDevices() {
        ArrayList<String> devices = new ArrayList<>();
        try {
            String line;
            Process getConnectedDevices = Runtime.getRuntime().exec("idevice_id -l");
            BufferedReader input = new BufferedReader(new InputStreamReader(getConnectedDevices.getInputStream()));
            while ((line = input.readLine()) != null) {
                MyLogger.logSys("Found iOS device :: " + line + "\n");
                if (line.length() > 0) {
                    devices.add(line);
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }

    public static String getDeviceName(String udid) {
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

    void closeIOSKeyboard() {
        DriverManager.getIosDriver().hideKeyboard();
    }

    void clickIOSNativeCancelButton() {
        getElementByAccessibilityLabel("Cancel").findAndClick();
    }
}
