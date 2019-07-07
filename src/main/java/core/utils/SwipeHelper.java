package core.utils;

import core.constants.SwipeDirection;
import core.constants.SwipeDirection.Direction;
import core.utils.Log.TextColor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static core.constants.SwipeDirection.Direction.DOWN;
import static core.managers.drivers.DriverManager.isIOS;
import static core.utils.ElementWrapper.waitForElementToAppear;

class SwipeHelper {

    private TouchAction action;
    private SwipeDirection swipeDirection;

    SwipeHelper(AppiumDriver driver) {
        swipeDirection = new SwipeDirection(driver);
        action = new TouchAction(driver);
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
    void swipe(Direction direction) {

        int[] point = swipeDirection.getDirection(direction);
        Log.info("Swiping " + direction);

        if (isIOS) {
            int endX = point[2] - point[0];
            int endY = point[3] - point[1];

            swipe(point[0], point[1], endX, endY);
        }

        swipe(point[0], point[1], point[2], point[3]);
    }

    void swipeElementToDirection(WebElement element, Direction direction) {

        final int elementHeight = element.getRect().getHeight();
        final int elementWidth = element.getRect().getWidth();

        final int elementY = element.getRect().getY();
        final int elementX = element.getRect().getX();

        final int elementCenterX = (elementWidth / 2) + elementX;
        final int elementCenterY = (elementHeight / 2) + elementY;

        final int[] point = swipeDirection.getDirection(direction);
        Log.info("Swiping Element " + direction);

        switch (direction) {
            case UP:
                swipe(elementCenterX, elementCenterY, elementCenterX, point[3]);
                break;

            case DOWN:
                swipe(elementCenterX, elementCenterY, elementCenterX, point[3]);
                break;

            case RIGHT:
                swipe(elementCenterX, elementCenterY, point[2], elementCenterY);
                break;

            case LEFT:
                swipe(elementCenterX, elementCenterY, point[2], elementCenterY);
                break;
        }
    }

    void swipeElementToCustomDirection(WebElement element, final int endX, final int endY) {

        final int elementHeight = element.getRect().getHeight();
        final int elementWidth = element.getRect().getWidth();

        final int elementY = element.getRect().getY();
        final int elementX = element.getRect().getX();

        final int[] elementCentre = new int[2];

        elementCentre[0] = (elementWidth / 2) + elementX;
        elementCentre[1] = (elementHeight / 2) + elementY;

        Log.info("Swiping Element to custom coordinates [" + endX + "," + endY + "]");

        swipe(elementCentre[0], elementCentre[1], endX, endY);
    }

    void swipe(int startX, int startY, int endX, int endY) {
        action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(endX, endY)).release().perform();
    }

    String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
    }
}