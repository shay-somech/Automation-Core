package core.managers;

import core.managers.drivers.DriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;

public class DisplayManager {

    public static boolean isPortrait;
    private static DisplayManager instance;

    private int deviceHeight;
    private int deviceWidth;

    private DisplayManager() {
        isPortrait = DriverManager.getDriver().getOrientation().equals(ScreenOrientation.PORTRAIT);
    }

    public static DisplayManager getInstance() {
        if (instance == null)
            instance = new DisplayManager();
        return instance;
    }

    public int getDeviceHeight() {
        if (isPortrait) {
            deviceHeight = DriverManager.getDriver().manage().window().getSize().height;
        } else {
            deviceHeight = DriverManager.getDriver().manage().window().getSize().width;
        }

        return deviceHeight;
    }

    public int getDeviceWidth() {
        if (isPortrait) {
            deviceWidth = DriverManager.getDriver().manage().window().getSize().width;
        } else {
            deviceWidth = DriverManager.getDriver().manage().window().getSize().height;
        }

        return deviceWidth;
    }

    public Dimension getDeviceDimensions() {
        return new Dimension(getDeviceWidth(), getDeviceHeight());
    }

}
