package core.managers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;

public class DisplayManager {

    private AppiumDriver driver;
    public static boolean isPortrait;

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

}
