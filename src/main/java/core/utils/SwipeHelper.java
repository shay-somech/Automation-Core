package core.utils;

import core.utils.Log.TextColor;
import core.utils.SwipeDirectionHandler.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static core.utils.ElementWrapper.waitForElementToAppear;
import static core.utils.SwipeDirectionHandler.Direction.*;

class SwipeHelper {

    private TouchAction action;
    private SwipeDirectionHandler swipeDirectionHandler;

    SwipeHelper(AppiumDriver driver) {
        swipeDirectionHandler = new SwipeDirectionHandler(driver);
        action = new TouchAction(driver);
    }

    void swipe(int startX, int startY, int endX, int endY) {
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY))
                .release().perform();
    }

    void swipe(Direction direction) {
        final Point startPoints = swipeDirectionHandler.getStartPoints(direction);
        final Point endPoints = swipeDirectionHandler.getEndPoints(direction);
        Log.info("Swiping " + direction);
        swipe(startPoints.getX(), startPoints.getY(), endPoints.getX(), endPoints.getY());
    }

    void swipeElementToDirection(WebElement element, Direction direction, SwipeDirectionHandler.Position swipePosition) {
        final Point endPoints = swipeDirectionHandler.getEndPoints(direction);
        final Point elementPosition = swipeDirectionHandler.getElementSwipePosition(element, swipePosition);
        Log.info("Swiping Element " + direction);

        if (direction == UP || direction == DOWN) {
            swipe(elementPosition.getX(), elementPosition.getY(), elementPosition.getX(), endPoints.getY());
            return;
        }

        if (direction == RIGHT || direction == LEFT) {
            swipe(elementPosition.getX(), elementPosition.getY(), endPoints.getX(), elementPosition.getY());
        }
    }

    void swipeElementToCustomDirection(WebElement element, SwipeDirectionHandler.Position swipePosition, final int endX, final int endY) {
        final Point elementPosition = swipeDirectionHandler.getElementSwipePosition(element, swipePosition);
        Log.info("Swiping Element " + swipePosition + " position to custom coordinates [" + endX + "," + endY + "]");
        swipe(elementPosition.getX(), elementPosition.getY(), endX, endY);
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
     *
     * @param uiSelector Android only
     */
    String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
    }
}