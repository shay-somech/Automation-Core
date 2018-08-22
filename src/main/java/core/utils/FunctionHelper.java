package core.utils;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.managers.drivers.IOSDriverManager;
import org.openqa.selenium.ScreenOrientation;

import static core.managers.DisplayManager.isPortrait;

public class FunctionHelper {

    private static FunctionHelper instance;

    public static FunctionHelper getInstance() {
        if (instance == null)
            instance = new FunctionHelper();
        return instance;
    }

    public static void wait(int seconds) {
        try {
            // TODO: Fix this method
            if (seconds > 60) {
                int splitSeconds = seconds / 3;
                for (int i = 0; i < 3; i++) {
                    MyLogger.logSys("Duration is bigger than threshold, dividing Seconds by 3 ");
                    Thread.sleep(splitSeconds * 1000);
                }
            } else
                Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void changeDeviceOrientation(ScreenOrientation orientation) {
        if (DriverManager.getDriver().getOrientation() != ScreenOrientation.LANDSCAPE) {
            isPortrait = false;
            MyLogger.logSys("Changing device orientation to " + orientation);
            DriverManager.getDriver().rotate(orientation);

        } else if (DriverManager.getDriver().getOrientation() != ScreenOrientation.PORTRAIT) {
            isPortrait = true;
            MyLogger.logSys("Changing device orientation to " + orientation);
            DriverManager.getDriver().rotate(orientation);

        } else {
            MyLogger.logSys("Cannot change to " + orientation + ", Please make sure device is not already in " + orientation);
        }
    }

    void closeApp() {
        MyLogger.logSys("Closing App...");
        DriverManager.getDriver().closeApp();
    }

    void launchApp() {
        MyLogger.logSys("Launching App...");
        DriverManager.getDriver().launchApp();
    }

    /**
     * This method extracts the Video duration from the given Element then wait for the entire duration
     */
    void playEntireVideo(ElementWrapper videoDurationElement) {

        videoDurationElement.findAndClick();
        String videoLength = videoDurationElement.getText();
        System.out.println("Video length == " + videoLength);

        int videoDuration = getElementDuration(videoLength);

        /** Waiting for the entire Video duration */
        System.out.println("Waiting for :: " + videoDuration + " seconds to Video end");
        wait(videoDuration);
    }

    /**
     * Method below receives String and converts it to Integer, mostly used for extracting video element duration
     */
    private int getElementDuration(String videoLength) {
        String videoMinutes;
        String videoSeconds;
        String iosVideoMinutes;
        int minutes;

        videoMinutes = videoLength.substring(0, videoLength.indexOf(":"));
        videoSeconds = videoLength.substring(videoLength.indexOf(":") + 1, videoLength.length());

        if (IOSDriverManager.isIOS) {
            iosVideoMinutes = videoMinutes.replace("-", "");
            minutes = Integer.parseInt(iosVideoMinutes);
        } else {
            minutes = Integer.parseInt(videoMinutes);
        }

        int seconds = Integer.parseInt(videoSeconds);
        return ((minutes * 60) + seconds);
    }
}