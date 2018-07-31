package core.configurationPopUp;

import core.managers.MyLogger;
import core.managers.baseclasses.DeviceManager;
import core.managers.baseclasses.DeviceManager.Platform;
import core.utils.ADBHelper;
import core.utils.IOSHelper;
import io.appium.java_client.remote.MobileCapabilityType;
import javafx.scene.control.ChoiceBox;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static core.utils.ADBHelper.*;
import static core.utils.IOSHelper.getDeviceName;

public class DropDownExecutioner {

    public static DesiredCapabilities dc = new DesiredCapabilities();
    private static ArrayList androidDevices = ADBHelper.getConnectedDevices();
    private static ArrayList iOSDevices = IOSHelper.getConnectedDevices();
    private static final String Android = "Android";
    private static final String iOS = "iOS";

    //    To get the value of the selected item
    static void instrumentApp(ChoiceBox<String> choiceBox) {
        dc.setCapability("instrumentApp", Boolean.parseBoolean(choiceBox.getValue()));
    }

    static void noReset(ChoiceBox<String> choiceBox) {
        dc.setCapability(MobileCapabilityType.NO_RESET, Boolean.parseBoolean(choiceBox.getValue()));
    }

    static void selectDevice(ChoiceBox<String> choiceBox) {
        dc.setCapability(MobileCapabilityType.UDID, (choiceBox.getValue().substring(choiceBox.getValue().indexOf("|| ") + 3)));
    }

    static void setPlatform(ChoiceBox<String> choiceBox) {
        switch (choiceBox.getValue()) {
            case Android:
                DeviceManager.setPlatform(Platform.Android);
                break;

            case iOS:
                DeviceManager.setPlatform(Platform.Apple);
                break;
        }
    }

    static ArrayList getAvailableAndroidDevices() {
        ArrayList<String> availableDevices = new ArrayList<>();

        MyLogger.logSys("Checking for available Android devices");
        for (Object connectedAndroidDevice : androidDevices) {
            String androidDevice = getDeviceModel(connectedAndroidDevice.toString()) + " " + getDeviceManufacturer(connectedAndroidDevice.toString()) + " " + getAndroidVersionAsString(connectedAndroidDevice.toString()) + " || " + connectedAndroidDevice.toString();
            availableDevices.add(androidDevice);
        }

        if (availableDevices.size() == 0)
            MyLogger.logSys("Not a single Android device is available for testing at this time");

        return availableDevices;
    }

    static ArrayList getAvailableIOSDevices() {
        ArrayList<String> availableDevices = new ArrayList<>();

        MyLogger.logSys("Checking for available iOS devices");
        for (Object connectedIOSDevice : iOSDevices) {
            String iOSDevice = getDeviceName(connectedIOSDevice.toString()) + " || " + connectedIOSDevice.toString();
            availableDevices.add(iOSDevice);
        }

        if (availableDevices.size() == 0)
            MyLogger.logSys("Not a single iOS device is available for testing at this time");

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

    //Run testNG programmatically by Class
    static void runTestNGSuite(String className) {
        MyLogger.logSys("Running TestNG");
        List<XmlSuite> suites = new ArrayList<>();
        List<XmlClass> classes = new ArrayList<>();

        XmlSuite suite = new XmlSuite();
        suite.setName("AutomationSuite");

        XmlTest test = new XmlTest(suite);
        test.setName("MyTest");

        XmlClass class1 = new XmlClass(className);
        classes.add(class1);

        test.setXmlClasses(classes);

        suites.add(suite);

        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}