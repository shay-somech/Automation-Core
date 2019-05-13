package core.utils;

import com.google.common.collect.ImmutableMap;
import core.constants.KeyboardEvents;
import core.constants.SwipeDirection;
import core.managers.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;

public class ActionHelper {

    private final AppiumDriver driver;
    private final SwipeHelper swipeHelper;
    private final FunctionHelper functionHelper;
    private final AndroidHelper androidHelper;
    private final TouchActionsHelper touchActions;
    private final MobilePhysicalKeyEvents physicalKeyEvents;
    private static ActionHelper instance;

    private ActionHelper() {
        Log.info("ActionHelper created");
        driver = DriverManager.getDriver();
        swipeHelper = new SwipeHelper(driver);
        functionHelper = new FunctionHelper(driver);
        androidHelper = new AndroidHelper((AndroidDriver) driver);
        touchActions = new TouchActionsHelper(driver);
        physicalKeyEvents = new MobilePhysicalKeyEvents(driver, (AndroidDriver) driver);
        Log.info("ActionHelper initialized");
    }

    public static ActionHelper getInstance() {
        if (instance == null)
            instance = new ActionHelper();
        return instance;
    }

    /**
     * Appium Generic functionality handlers
     */

    public void wait(int seconds) {
        try {
            synchronized (driver) {
                driver.wait(seconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setAppContext(String zone) {
        Log.info("Switching App Context to :: " + zone);
        driver.context(zone);
    }

    public void getAppContext() {
        Log.info("Getting App Context");
        driver.getContext();
    }

    public void closeApp() {
        Log.info("Closing App...");
        driver.closeApp();
    }

    public void launchApp() {
        Log.info("Launching App...");
        driver.launchApp();
    }

    public void changeDeviceOrientation(ScreenOrientation orientation) {
        functionHelper.changeDeviceOrientation(orientation);
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public void setDeviceGeoLocation(double latitude, double longitude, double altitude) {
        Location location = new Location(latitude, longitude, altitude);
        driver.setLocation(location);
    }

    public void tapDevicePhysicalKey(MobilePhysicalKeyEvents.KeyEvents keyEvent) {
        physicalKeyEvents.pressDevicePhysicalKey(keyEvent);
    }

    /**
     * Swipe handlers
     */

    public boolean swipeDownUntilElementFound(WebElement webElement, int maxSwipes, boolean failIfNotFound) {
        return swipeHelper.swipeDownUntilElementFound(webElement, maxSwipes, failIfNotFound);
    }

    public boolean swipeDownUntilElementFound(WebElement webElement) {
        return swipeHelper.swipeDownUntilElementFound(webElement);
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

    public void scrollToView(String uiSelector) {
        swipeHelper.UiScrollable(uiSelector);
    }

    /**
     * Gestures handlers
     */

    public void tap(int x, int y) {
        touchActions.tap(x, y);
    }

    void pressAndMoveElementToCustomCoordinate(WebElement element, int xOffset, int yOffset) {
        touchActions.pressMoveElementToOffset(element, xOffset, yOffset);
    }

    void doubleClickElement(WebElement webElement) {
        touchActions.doubleClickElement(webElement);
    }

    void dragAndDropElement(WebElement element, int xOffset, int yOffset) {
        touchActions.dragAndDropElement(element, xOffset, yOffset);
    }

    void clickAndHoldElement(WebElement webElement) {
        touchActions.clickAndHoldElement(webElement);
    }

    void longPressElement(WebElement element) {
        touchActions.longPressElement(element);
    }

    void longPressPoint(int x, int y) {
        touchActions.longPressPoint(x, y);
    }

    /**
     * Android functionality handlers
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

    public void getNetworkConnection() {
        androidHelper.getNetworkConnection();
    }

    public void setNetworkConnection(boolean airplane, boolean wifi, boolean date) {
        androidHelper.setNetworkConnection(airplane, wifi, date);
    }
}