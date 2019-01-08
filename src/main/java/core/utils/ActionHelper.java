package core.utils;

import com.google.common.collect.ImmutableMap;
import core.constants.KeyboardEvents;
import core.constants.SwipeDirection;
import core.managers.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class ActionHelper {

    private final AppiumDriver driver;
    private final SwipeHelper swipeHelper;
    private final FunctionHelper functionHelper;
    private final AndroidHelper androidHelper;
    private final IOSHelper iosHelper;
    private static ActionHelper instance;

    private ActionHelper() {
        Log.info("ActionHelper created");
        driver = DriverManager.getDriver();
        swipeHelper = new SwipeHelper(driver);
        functionHelper = new FunctionHelper(driver);
        androidHelper = new AndroidHelper();
        iosHelper = new IOSHelper();
        Log.info("ActionHelper initialized");
    }

    public static ActionHelper getInstance() {
        if (instance == null)
            instance = new ActionHelper();
        return instance;
    }

    public void swipeDownUntilElementFound(WebElement webElement, boolean failIfNotFound) {
        swipeHelper.swipeDownUntilElementFound(webElement, failIfNotFound);
    }

    public void swipeDownUntilElementFound(WebElement webElement) {
        swipeHelper.swipeDownUntilElementFound(webElement);
    }

    public void swipe(SwipeDirection direction) {
        swipeHelper.swipe(direction);
    }

    public void swipe(int startX, int endX, int startY, int endY) {
        swipeHelper.swipe(startX, startY, endX, endY);
    }

    public void customHorizontalSwipe(WebElement webElement, SwipeDirection direction) {
        swipeHelper.customHorizontalSwipe(webElement.getRect().getY(), direction);
    }

    public void pullToRefresh() {
        swipeHelper.swipeUpOnce();
    }


    /**
     * Appium Core functionality Handlers
     */

    public void wait(int seconds) {
        functionHelper.wait(seconds);
    }

    public void setAppContext(String zone) {
        Log.info("Switching App Context to :: " + zone);
        driver.context(zone);
    }

    public void closeApp() {
        functionHelper.closeApp();
    }

    public void launchApp() {
        functionHelper.launchApp();
    }

    public void changeDeviceOrientation(ScreenOrientation orientation) {
        functionHelper.changeDeviceOrientation(orientation);
    }


    /**
     * Android Functionality Handlers
     */

    public void clickAndroidBackButton() {
        androidHelper.clickAndroidBackButton();
    }

    public void openAndroidNotificationCenter() {
        androidHelper.openAndroidNotificationCenter();
    }

    public void startAndroidActivity(String appPackage, String appMainActivity) {
        androidHelper.startAndroidActivity(appPackage, appMainActivity);
    }

    public String getAndroidCurrentActivity() {
        return androidHelper.getAndroidCurrentActivity();
    }

    public void sendKeyboardEvent(KeyboardEvents events) {
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", events.toString()));
    }

    public void luanchAndroidSettings() {
        androidHelper.launchAndroidSettings();
    }


    /**
     * IOS Functionality handlers
     */

    public void clickIOSNativeCancelButton() {
        iosHelper.clickIOSNativeCancelButton();
    }


    /**
     * General Handlers
     */

    public void playEntireVideo(WebElement videoDurationElement) {
        functionHelper.playEntireVideo(videoDurationElement);
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }
}