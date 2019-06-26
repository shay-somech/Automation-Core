package core.utils;

import core.constants.SwipeDirection;
import core.managers.DisplayManager;
import core.managers.drivers.DriverManager;
import core.utils.Log.TextColor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static core.constants.SwipeDirection.DOWN;
import static core.utils.ElementWrapper.waitForElementToAppear;

class SwipeHelper {

    private static int deviceWidth;
    private static int deviceHeight;
    private TouchAction action;

    SwipeHelper(AppiumDriver driver) {
        DisplayManager displayManager = new DisplayManager(driver);
        action = new TouchAction(driver);
        deviceWidth = displayManager.getDeviceWidth();
        deviceHeight = displayManager.getDeviceHeight();
    }

    /**
     * swipe down until element provided is found , will try to find the element pre-swiping
     *
     * @param element        element we want to find
     * @param maxSwipes      Maximum number of swipes before exiting the swipe loop
     * @param failIfNotFound should throw exception if element was not found in the maxSwipes limit
     */
    boolean swipeDownUntilElementFound(WebElement element, int maxSwipes, boolean failIfNotFound) {

        Log.info(TextColor.ANSI_GREEN, "start swipe for element : " + element.toString());

        try {
            waitForElementToAppear(element, 1);
            Log.info(TextColor.ANSI_GREEN, "element found before swiping down");
            return true;

        } catch (TimeoutException e) {
            Log.info(TextColor.ANSI_RED, "element was not found, swiping down");

            for (int i = 0; i < maxSwipes; i++) {
                swipe(DOWN);

                try {
                    waitForElementToAppear(element, 1);
                    return true;

                } catch (TimeoutException ignore) {
                    Log.info(TextColor.ANSI_RED, "element was not found, continuing to swipe down ...");
                }
            }
        }

        if (failIfNotFound)
            throw new RuntimeException("Can't navigate , element absent or blocked");
        else
            return false;
    }

    boolean swipeDownUntilElementFound(WebElement webElement) {
        return swipeDownUntilElementFound(webElement, 20, false);
    }

    /**
     * @param direction int startX, startY, endX, endY are calculated as part of the Device height and width
     * @method swipe() implements TouchAction press.MoveTo, moveTo coordinates are relative to the current position.
     * meaning this method adds the startX&startY to endX&endY by default, in order to avoid it we subtracting startX from endX.
     * The Multiplication operations representing percentage of the screen you want to swipe from -> to
     */
    void swipe(SwipeDirection direction) {
        int startX = deviceWidth;
        int startY = deviceHeight;
        int endX = deviceWidth;
        int endY = deviceHeight;

        switch (direction) {
            case UP:
                if (DriverManager.isIOS) {
                    int startUpX = startX / 2;
                    int startUpY = (int) (startY * 0.3);
                    int vectorUpX = endX / 2 - startUpX;
                    int vectorUpY = (int) (endY * 0.8 - startUpY);

                    swipe(startUpX, startUpY, vectorUpX, vectorUpY);
                } else {
                    swipe(startX / 2, (int) (startY * 0.3), endX / 2, (int) (endY * 0.8));
                }
                break;

            case DOWN:
                if (DriverManager.isIOS) {
                    int startDownX = startX / 2;
                    int startDownY = (int) (startY * 0.8);
                    int vectorDownX = endX / 2 - startDownX;
                    int vectorDownY = (int) (endY * 0.3 - startDownY);

                    swipe(startDownX, startDownY, vectorDownX, vectorDownY);
                } else {
                    swipe(startX / 2, (int) (startY * 0.8), endX / 2, (int) (endY * 0.3));
                }
                break;

            case LEFT:
                if (DriverManager.isIOS) {
                    int startLeftX = (int) (startX * 0.8);
                    int startLeftY = startY / 2;
                    int vectorLeftX = (int) (endX * 0.1 - startLeftX);
                    int vectorLeftY = endY / 2 - startLeftY;

                    swipe(startLeftX, startLeftY, vectorLeftX, vectorLeftY);
                } else {
                    swipe((int) (startX * 0.8), startY / 2, (int) (endX * 0.1), (endY / 2));
                }
                break;

            case RIGHT:
                if (DriverManager.isIOS) {
                    int startRightX = (int) (startX * 0.1);
                    int startRightY = startY / 2;
                    int vectorRightX = (int) (endX * 0.8 - startRightX);
                    int vectorRightY = endY / 2 - startRightY;

                    swipe(startRightX, startRightY, vectorRightX, vectorRightY);
                } else {
                    swipe((int) (startX * 0.1), startY / 2, (int) (endX * 0.8), endY / 2);
                }
                break;
        }
        Log.info("Swiping " + direction);
    }

    void customHorizontalSwipe(final int elementCentreY, SwipeDirection direction) {
        int startX = deviceWidth;
        int endX = deviceWidth;
        int y = deviceHeight;

        switch (direction) {
            case LEFT:
                if (DriverManager.isIOS) {
                    int startLeftX = (int) (startX * 0.8);
                    int startLeftY = (y - (elementCentreY / 2));
                    int vectorLeftX = (int) (endX * 0.1 - startLeftX);
                    int vectorLeftY = (y - (elementCentreY / 2 - startLeftY));

                    swipe(startLeftX, startLeftY, vectorLeftX, vectorLeftY);
                } else {
                    swipe((int) (startX * 0.8), (y - (elementCentreY / 2)), (int) (endX * 0.1), (y - (elementCentreY / 2)));
                }

            case RIGHT:
                if (DriverManager.isIOS) {
                    int startRightX = (int) (startX * 0.2);
                    int startRightY = (y - (elementCentreY / 2));
                    int vectorRightX = (int) (endX * 0.8 - startRightX);
                    int vectorRightY = ((startRightY) - (y - (elementCentreY / 2)));

                    swipe(startRightX, startRightY, vectorRightX, vectorRightY);
                } else {
                    swipe((int) (startX * 0.1), (y - (elementCentreY / 2)), (int) (endX * 0.8), (y - (elementCentreY / 2)));
                }
        }
        Log.info("Swiping " + direction);
    }

    void swipe(int startX, int startY, int endX, int endY) {
        action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(endX, endY)).release().perform();
    }

    String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
    }
}