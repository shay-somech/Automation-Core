package core.utils;

import core.constants.SwipeDirection;
import core.managers.DisplayManager;
import core.managers.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static core.constants.SwipeDirection.DOWN;
import static core.constants.SwipeDirection.UP;
import static core.utils.ElementWrapper.waitForElementToAppear;

class SwipeHelper {

    private static int deviceWidth;
    private static int deviceHeight;
    private TouchAction action;

    SwipeHelper(AppiumDriver driver) {
        action = new TouchAction(driver);
        deviceWidth = DisplayManager.getInstance().getDeviceWidth();
        deviceHeight = DisplayManager.getInstance().getDeviceHeight();
    }

    /**
     * swipe down until element provided is found , will try to find the element pre-swiping, will swipe down 20 times before throwing an AssertionError
     *
     * @param element element we want to find
     */
    void swipeDownUntilElementFound(WebElement element, boolean failIfNotFound) {

        Log.info("start swipe for element : " + element.toString());
        boolean found = false;

        try {
            waitForElementToAppear(element, 1);
            Log.info("element found before swiping down");
            found = true;

        } catch (TimeoutException e) {
            Log.info("element was not found, start swiping down");

            for (int i = 0; i < 20; i++) {
                swipe(DOWN);

                try {
                    if (waitForElementToAppear(element, 1).isDisplayed()) {
                        found = true;
                        break;
                    }
                } catch (TimeoutException ignore) {
                    Log.info("element was not found, continuing to swipe down ...");
                }
            }
        }

        if (!found && failIfNotFound)
            throw new AssertionError("Can't navigate , element absent or blocked");
    }

    void swipeDownUntilElementFound(WebElement webElement) {
        swipeDownUntilElementFound(webElement, false);
    }

    void swipeUpOnce() {
        swipe(UP);
    }

    /**
     * @param direction int startX, startY, endX, endY are calculated as part of the Device height and width
     * @method swipe() implements TouchAction press.MoveTo, moveTo coordinates are relative to the current position.
     * meaning this method adds the startX&startY to endX&endY by default, in order to avoid it we subtracting startX from endX.
     * The Multiplication operations representing percentage of the screen you want to swipe from > to
     */
    void swipe(SwipeDirection direction) {
        int startX = deviceWidth;
        int startY = deviceHeight;
        int endX = deviceWidth;
        int endY = deviceHeight;

        switch (direction) {
            case UP:
                if (DriverManager.isIOS) {
                    int startUpX = (int) (startX * 0.5);
                    int startUpY = (int) (startY * 0.3);
                    int vectorUpX = (int) (endX * 0.5 - startUpX);
                    int vectorUpY = (int) (endY * 0.8 - startUpY);

                    swipe(startUpX, startUpY, vectorUpX, vectorUpY);
                } else {
                    swipe((int) (startX * 0.5), (int) (startY * 0.3), (int) (endX * 0.5), (int) (endY * 0.8));
                }
                break;

            case DOWN:
                if (DriverManager.isIOS) {
                    int startDownX = (int) (startX * 0.5);
                    int startDownY = (int) (startY * 0.8);
                    int vectorDownX = (int) (endX * 0.5 - startDownX);
                    int vectorDownY = (int) (endY * 0.3 - startDownY);

                    swipe(startDownX, startDownY, vectorDownX, vectorDownY);
                } else {
                    swipe((int) (startX * 0.5), (int) (startY * 0.8), (int) (endX * 0.5), (int) (endY * 0.3));
                }
                break;

            case LEFT:
                if (DriverManager.isIOS) {
                    int startLeftX = (int) (startX * 0.8);
                    int startLeftY = (int) (startY * 0.5);
                    int vectorLeftX = (int) (endX * 0.2 - startLeftX);
                    int vectorLeftY = (int) (endY * 0.5 - startLeftY);

                    swipe(startLeftX, startLeftY, vectorLeftX, vectorLeftY);
                } else {
                    swipe((int) (startX * 0.8), (int) (startY * 0.5), (int) (endX * 0.2), (int) (endY * 0.5));
                }
                break;

            case RIGHT:
                if (DriverManager.isIOS) {
                    int startRightX = (int) (startX * 0.1);
                    int startRightY = (int) (startY * 0.5);
                    int vectorRightX = (int) (endX * 0.8 - startRightX);
                    int vectorRightY = (int) (endY * 0.5 - startRightY);

                    swipe(startRightX, startRightY, vectorRightX, vectorRightY);
                } else {
                    swipe((int) (startX * 0.1), (int) (startY * 0.5), (int) (endX * 0.8), (int) (endY * 0.5));
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
                    int startLeftY = (int) (y - (elementCentreY * 0.5));
                    int vectorLeftX = (int) (endX * 0.2 - startLeftX);
                    int vectorLeftY = (int) (y - (elementCentreY * 0.5 - startLeftY));

                    swipe(startLeftX, startLeftY, vectorLeftX, vectorLeftY);
                } else {
                    swipe((int) (startX * 0.8), (int) (y - (elementCentreY * 0.5)), (int) (endX * 0.2), (int) (y - (elementCentreY * 0.5)));
                }

            case RIGHT:
                if (DriverManager.isIOS) {
                    int startRightX = (int) (startX * 0.2);
                    int startRightY = (int) (y - (elementCentreY * 0.5));
                    int vectorRightX = (int) (endX * 0.8 - startRightX);
                    int vectorRightY = (int) ((startRightY) - (y - (elementCentreY * 0.5)));

                    swipe(startRightX, startRightY, vectorRightX, vectorRightY);
                } else {
                    swipe((int) (startX * 0.1), (int) (y - (elementCentreY * 0.5)), (int) (endX * 0.8), (int) (y - (elementCentreY * 0.5)));
                }
        }
        Log.info("Swiping " + direction);
    }

    void swipe(int startX, int startY, int endX, int endY) {
        action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(endX, endY)).release().perform();
    }
}