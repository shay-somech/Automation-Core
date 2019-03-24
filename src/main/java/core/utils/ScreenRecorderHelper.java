package core.utils;

import core.managers.drivers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;

public class ScreenRecorderHelper {

    private AndroidStartScreenRecordingOptions androidStartScreenRecordingOptions = new AndroidStartScreenRecordingOptions();
    private IOSStartScreenRecordingOptions iOSStartScreenRecordingOptions = new IOSStartScreenRecordingOptions();

    public AndroidStartScreenRecordingOptions androidRecordingOptions() {
        return androidStartScreenRecordingOptions;
    }

    public IOSStartScreenRecordingOptions iOSRecordingOptions() {
        return iOSStartScreenRecordingOptions;
    }

    public void startRecording(BaseStartScreenRecordingOptions baseStartScreenRecordingOptions) {
        if (DriverManager.getDriver() instanceof AndroidDriver) {
            DriverManager.getAndroidDriver().startRecordingScreen(baseStartScreenRecordingOptions);
        } else {
            DriverManager.getIOSDriver().startRecordingScreen(baseStartScreenRecordingOptions);
        }
    }

    public void stopRecording() {
        if (DriverManager.getDriver() instanceof AndroidDriver) {
            DriverManager.getAndroidDriver().stopRecordingScreen();
        } else {
            DriverManager.getIOSDriver().stopRecordingScreen();
        }
    }
}
