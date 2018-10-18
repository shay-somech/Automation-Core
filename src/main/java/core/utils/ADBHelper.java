package core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ADBHelper {

    private static String ANDROID_HOME;
    private static String ID;

    public ADBHelper(String deviceID) {
        ID = deviceID;
    }

    public static class Commands {
        final public static String CLICK_SELECTED = "adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings";
        final public static String ARROW_UP = "adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings";
    }

    private void runADBMethod(String commandToRun) {
        try {
            Runtime.getRuntime().exec(commandToRun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String runCommand(String command) {
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) output = scanner.next();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }

    private static String getAndroidHome() {
        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null)
                throw new RuntimeException("Failed to find ANDROID_HOME, make sure the environment variable is set");
        }
        return ANDROID_HOME;
    }

    private static String command(String command) {
        Log.info("Formatting ADB Command: " + command);

        if (command.startsWith("adb")) {
            command = command.replace("adb ", getAndroidHome() + "/platform-tools/adb ");
        } else {
            throw new RuntimeException("This method is designed to run ADB commands only!");
        }

        String output = runCommand(command);
        if (output == null) return "";
        else return output.trim();
    }

    public static void killServer() {
        command("adb kill-server");
    }

    public static void startServer() {
        command("adb start-server");
    }

    public static ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        return devices;
    }

    public String getForegroundActivity() {
        return command("adb -s " + ID + " shell dumpsys window windows | grep mCurrentFocus");
    }

    public static String getAndroidVersionAsString(String ID) {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        if (output.length() == 3) output += ".0";
        return output;
    }

    public static int getAndroidVersion(String ID) {
        return Integer.parseInt(getAndroidVersionAsString(ID).replaceAll("\\.", ""));
    }

    public static ArrayList getInstalledPackages() {
        ArrayList packages = new ArrayList();
        String[] output = command("adb shell pm list packages").split("\n");
        for (String packageID : output) packages.add(packageID.replace("package:", "").trim());
        return packages;
    }

    public static void WiFiConnection(boolean enabled) {
        if (enabled) {
            command("adb shell -s svc wifi enable");
        } else {
            command("adb shell -s svc wifi disable");
        }
    }

    public static void AirPlaneModeConncetion(boolean enabled) {
        if (enabled) {
            command("adb shell settings put global airplane_mode_on 1");
            command("adb shell am broadcast -a android.intent.action.AIRPLANE_MODE");
        } else {
            command("adb shell settings put global airplane_mode_on 0");
            command("adb shell am broadcast -a android.intent.action.AIRPLANE_MODE");
        }
    }

    public void openWiFiSettingsScreen() {
        command("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
    }

    public static String getAppVersionNumber(String appPackage) {
        String output = command("adb shell dumpsys package " + appPackage + " | grep versionName");
        return output.replace("versionName=", "").trim();
    }

    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    public void clearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " uninstall " + packageID);
    }

    public void clearLogBuffer() {
        command("adb -s " + ID + " shell -c");
    }

    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    public void deleteFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    public void takeScreenshot(String target) {
        command("adb -s " + ID + " shell screencap " + target);
    }

    public void rebootDevice() {
        command("adb -s " + ID + " reboot");
    }

    public static String getDeviceModel(String ID) {
        return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    public static String getDeviceManufacturer(String ID) {
        return command("adb -s " + ID + " shell getprop ro.product.manufacturer");
    }

    public static String getDeviceSerialNumber() {
        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    public String getDeviceCarrier() {
        return command("adb -s " + ID + " shell getprop gsm.operator.alpha");
    }

    public ArrayList getLogcatProcesses() {
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList processes = new ArrayList();
        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

//    public Object startLogcat(final String logID, final String grep) {
//        ArrayList pidBefore = getLogcatProcesses();
//
//        Thread logcat = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (grep == null) command("adb -s " + ElementsById + " shell logcat -v threadtime > /sdcard/" + logID + ".txt");
//                else
//                    command("adb -s " + ElementsById + " shell logcat -v threadtime | grep -i '" + grep + "'> /sdcard/" + logID + ".txt");
//            }
//        });
//        logcat.setName(logID);
//        logcat.start();
//        logcat.interrupt();
//
//        ArrayList pidAfter = getLogcatProcesses();
//        Timer timer = new Timer();
//        timer.start();
//        while (!timer.expired(5)) {
//            if (pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
//            if (pidAfter.size() > 0) break;
//            pidAfter = getLogcatProcesses();
//        }
//
//        if (pidAfter.size() == 1) return pidAfter.get(0);
//        else if (pidAfter.size() > 1)
//            throw new RuntimeException("Multiple logcat processes were started when only one was expected!");
//        else throw new RuntimeException("Failed to start logcat process!");
//    }

    public void stopLocat(Object PID) {
        command("adb -s " + ID + " shell kill " + PID);
    }
}