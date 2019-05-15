package core.managers;

import core.utils.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;

import static core.utils.Log.TextColor.ANSI_RED;

public class DisplayManager {

    private AppiumDriver driver;
    private static boolean isPortrait;

    public DisplayManager(AppiumDriver driver) {
        this.driver = driver;
        isPortrait = driver.getOrientation().equals(ScreenOrientation.PORTRAIT);
    }

    public int getDeviceHeight() {
        int deviceHeight;

        if (isPortrait) {
            deviceHeight = driver.manage().window().getSize().height;
        } else {
            deviceHeight = driver.manage().window().getSize().width;
        }

        return deviceHeight;
    }

    public int getDeviceWidth() {
        int deviceWidth;

        if (isPortrait) {
            deviceWidth = driver.manage().window().getSize().width;
        } else {
            deviceWidth = driver.manage().window().getSize().height;
        }

        return deviceWidth;
    }

    public Dimension getDeviceDimensions() {
        return new Dimension(getDeviceWidth(), getDeviceHeight());
    }

    public void changeDeviceOrientation(ScreenOrientation orientation) {
        if (driver.getOrientation() != orientation) {
            Log.info("Changing device orientation to " + orientation);
            driver.rotate(orientation);

        } else {
            Log.info(ANSI_RED, "Cannot change Device orientation to " + orientation + ", Please make sure device is not already in " + orientation);
        }
    }
}
