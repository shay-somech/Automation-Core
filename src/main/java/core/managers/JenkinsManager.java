package core.managers;

import core.constants.PlatformType;
import core.utils.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static core.utils.AndroidHelper.getAndroidDevices;
import static core.utils.IOSHelper.getIOSDevices;

public class JenkinsManager {

    private static JenkinsManager instance;
    private boolean isJenkinsRunning;

    private JenkinsManager() {
        isJenkinsRunning = isJenkinsProcessRunning();
    }

    public static JenkinsManager getInstance() {
        if (instance == null) {
            instance = new JenkinsManager();
        }
        return instance;
    }

    /**
     * Checking for Jenkins process using Shell command
     *
     * @return boolean if Process matches criteria
     */
    private boolean isJenkinsProcessRunning() {
        Log.info("Checking for Jenkins running instance");
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("ps -few");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                if (line.toLowerCase().contains(("jenkins.war"))) {
                    Log.info("Jenkins instance Found");
                    return true;
                }
            }

        } catch (Exception e) {
            Log.info(e.toString());
            e.printStackTrace();
        }

        Log.info("Jenkins instance was not Found");
        return false;
    }

    public boolean getJenkinsInstance() {
        return isJenkinsRunning;
    }

    public PlatformType getJenkinsSelectedPlatform() {
        Log.info("About to run Jenkins on " + System.getenv("Platform") + " Platform");

        switch (System.getenv("Platform")) {
            case "Android":
                return PlatformType.ANDROID;

            case "iOS":
                return PlatformType.IOS;
        }
        throw new RuntimeException("Could not get selected Platform from Jenkins");
    }

    String getJenkinsDeviceId() {
        switch (getJenkinsSelectedPlatform()) {
            case ANDROID:
                return getAndroidDevices().get(0);

            case IOS:
                return getIOSDevices().get(0);
        }
        throw new RuntimeException("Device not selected, Platform is not defined in scope");
    }
}
