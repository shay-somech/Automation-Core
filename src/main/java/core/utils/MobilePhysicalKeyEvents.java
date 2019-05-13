package core.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static core.managers.drivers.DriverManager.isAndroid;

class MobilePhysicalKeyEvents {

    private AppiumDriver driver;
    private AndroidDriver androidDriver;

    MobilePhysicalKeyEvents(AppiumDriver driver, AndroidDriver androidDriver) {
        this.driver = driver;
        this.androidDriver = androidDriver;
    }

    public enum KeyEvents {
        SEARCH,
        POWER,
        BACK,
        HOME,
        CLEAR,
        VOLUME_UP,
        VOLUME_DOWN,
        VOLUME_MUTE,
        START,
    }

    void pressDevicePhysicalKey(KeyEvents keyEvent) {
        if (isAndroid) {
            switch (keyEvent) {
                case SEARCH:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.SEARCH));
                    break;

                case POWER:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.POWER));
                    break;

                case BACK:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
                    break;

                case HOME:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));

                case CLEAR:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.CLEAR));
                    break;

                case VOLUME_UP:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));

                case VOLUME_DOWN:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
                    break;

                case VOLUME_MUTE:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_MUTE));
                    break;

                case START:
                    androidDriver.pressKey(new KeyEvent(AndroidKey.BUTTON_START));
                    break;
            }

        } else {
            switch (keyEvent) {
                case HOME:
                    driver.executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
                    break;

                case VOLUME_UP:
                    driver.executeScript("mobile: pressButton", ImmutableMap.of("name", "volumeup"));
                    break;

                case VOLUME_DOWN:
                    driver.executeScript("mobile: pressButton", ImmutableMap.of("name", "volumedown"));
                    break;
            }
            throw new RuntimeException("iOS " + keyEvent + " Physical button is not defined in scope, Only Home, Volume Up/Down are permitted");
        }
    }
}
