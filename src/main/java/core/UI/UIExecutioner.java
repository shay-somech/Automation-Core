package core.UI;

import core.utils.Log;
import core.utils.AndroidHelper;
import core.utils.IOSHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static core.utils.ADBHelper.*;
import static core.utils.IOSHelper.getDeviceName;

public class UIExecutioner {

    private static ArrayList androidDevices = AndroidHelper.getAndroidDevices();
    private static ArrayList iOSDevices = IOSHelper.getIOSDevices();


    static ArrayList<String> getAvailableAndroidDevices() {
        ArrayList<String> availableDevices = new ArrayList<>();

        Log.info("Checking for available Android devices");
        for (Object connectedAndroidDevice : androidDevices) {
            String androidDevice = getDeviceModel(connectedAndroidDevice.toString()) + " " + getDeviceManufacturer(connectedAndroidDevice.toString()) + " " + getAndroidVersionAsString(connectedAndroidDevice.toString()) + " || " + connectedAndroidDevice.toString();
            availableDevices.add(androidDevice);
        }

        if (availableDevices.size() == 0)
            Log.info("Not a single Android device is available for testing at this time");

        return availableDevices;
    }

    static ArrayList<String> getAvailableIOSDevices() {
        ArrayList<String> availableDevices = new ArrayList<>();

        Log.info("Checking for available iOS devices");
        for (Object connectedIOSDevice : iOSDevices) {
            String iOSDevice = getDeviceName(connectedIOSDevice.toString()) + " || " + connectedIOSDevice.toString();
            availableDevices.add(iOSDevice);
        }

        if (availableDevices.size() == 0)
            Log.info("Not a single iOS device is available for testing at this time");

        return availableDevices;
    }

    static ArrayList getJavaClassNameByFolderPath(String directoryName) {
        File directory = new File(directoryName);
        ArrayList objectList = new ArrayList<>();
        ArrayList fileList = new ArrayList<>();

        // get all the files from a directory
        File[] files = directory.listFiles();

        //Returns folders list
        if (files != null) {
            objectList.addAll(Arrays.asList(files));
        }

        if (files != null) {
            for (File object : files) {
                if (object.isFile() && object.getAbsolutePath().contains(".java")) {
                    fileList.add(object);
                }

                if (object.isDirectory()) {
                    fileList.addAll(getJavaClassNameByFolderPath(object.getAbsolutePath()));
                }
            }
        }
        return fileList;
    }

    static ArrayList getXMLClassNameByFolderPath(String directoryName) {
        File directory = new File(directoryName);
        ArrayList objectList = new ArrayList();
        ArrayList fileList = new ArrayList<>();

        // get all the files from a directory
        File[] files = directory.listFiles();

        //Returns folders list
        if (files != null) {
            objectList.addAll(Arrays.asList(files));
        }

        if (files != null) {
            for (File object : files) {
                if (object.isFile() && object.getAbsolutePath().contains(".xml")) {
                    fileList.add(object);
                }

                if (object.isDirectory()) {
                    fileList.addAll(getXMLClassNameByFolderPath(object.getAbsolutePath()));
                }
            }
        }
        return fileList;
    }
}