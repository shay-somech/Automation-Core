package core.utils;

import core.managers.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import static core.managers.DisplayManager.isPortrait;

public class FunctionHelper {

    private static AppiumDriver driver;

    FunctionHelper(AppiumDriver driver) {
        FunctionHelper.driver = driver;
    }

    public void wait(int seconds) {
        try {
            driver.wait(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void changeDeviceOrientation(ScreenOrientation orientation) {
        if (driver.getOrientation() != ScreenOrientation.LANDSCAPE) {
            isPortrait = false;
            Log.info("Changing device orientation to " + orientation);
            driver.rotate(orientation);

        } else if (driver.getOrientation() != ScreenOrientation.PORTRAIT) {
            isPortrait = true;
            Log.info("Changing device orientation to " + orientation);
            driver.rotate(orientation);

        } else {
            Log.info("Cannot change to " + orientation + ", Please make sure device is not already in " + orientation);
        }
    }

    void closeApp() {
        Log.info("Closing App...");
        driver.closeApp();
    }

    void launchApp() {
        Log.info("Launching App...");
        driver.launchApp();
    }

    /**
     * This method extracts the Video duration from the given Element then wait for the entire duration
     */
    void playEntireVideo(WebElement videoDurationElement) {

        videoDurationElement.click();
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

        if (DriverManager.isIOS) {
            iosVideoMinutes = videoMinutes.replace("-", "");
            minutes = Integer.parseInt(iosVideoMinutes);
        } else {
            minutes = Integer.parseInt(videoMinutes);
        }

        int seconds = Integer.parseInt(videoSeconds);
        return ((minutes * 60) + seconds);
    }
}