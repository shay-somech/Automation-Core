package core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;

import static core.managers.DisplayManager.isPortrait;

class FunctionHelper {

    private static AppiumDriver driver;

    FunctionHelper(AppiumDriver driver) {
        FunctionHelper.driver = driver;
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
}