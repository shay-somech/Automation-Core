package core.utils;

import core.managers.DisplayManager;
import core.managers.drivers.DriverManager;
import core.managers.drivers.IOSDriverManager;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

import static core.constants.SwipeDirection.*;

public class SwipeHelper {
    private static SwipeHelper instance;

    private static int deviceWidth;
    private static int deviceHeight;

    private TouchAction action;

    SwipeHelper() {
        action = new TouchAction(DriverManager.getDriver());

        deviceWidth = DisplayManager.getInstance().getDeviceWidth();
        deviceHeight = DisplayManager.getInstance().getDeviceHeight();
    }

    public static SwipeHelper getInstance() {
        if (instance == null)
            instance = new SwipeHelper();
        return instance;
    }

    /**
     * swipe down until element provided is found , will try 20 swipes down before throw an AssertionError
     *
     * @param elementWrapper                 element we want to find
     * @param tryToFindAlsoBeforeSwipingDown determine if we want to try finding object before the first swipe
     */
    private void swipeDownUntilElementFound(ElementWrapper elementWrapper, boolean tryToFindAlsoBeforeSwipingDown, boolean failIfNotFound, boolean click) {

        Log.info("start swipe for element : " + elementWrapper.getXpath());
        boolean found = false;

        if (tryToFindAlsoBeforeSwipingDown && elementWrapper.find(1, false) && (elementWrapper.findAndReturn().getRect().y < (deviceHeight / 3))) {
            Log.info("element found before swiping down : " + elementWrapper.getXpath());
            found = true;
        } else {
            for (int i = 0; i < 20; i++) {
                swipe(DOWN);

                if (elementWrapper.find(1, false) && elementWrapper.isExistAndDisplayed()) {
                    found = true;
                    break;
                }
            }
        }
        if (!found && failIfNotFound)
            throw new AssertionError("Can't navigate , element absent or blocked");

        if (click) {
            elementWrapper.findAndClick();
        }
    }

    void swipeDownUntilElementFound(ElementWrapper elementWrapper) {
        swipeDownUntilElementFound(elementWrapper, true, false, false);
    }

    void swipeDownUntilElementFound(ElementWrapper elementWrapper, boolean click) {
        swipeDownUntilElementFound(elementWrapper, true, false, click);
    }

    void swipeDownUntilElementFound(ElementWrapper elementWrapper, boolean failIfNotFound, boolean click) {
        swipeDownUntilElementFound(elementWrapper, true, failIfNotFound, click);
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
    void swipe(String direction) {
        int startX = deviceWidth;
        int startY = deviceHeight;
        int endX = deviceWidth;
        int endY = deviceHeight;

        switch (direction) {
            case UP:
                if (IOSDriverManager.isIOS) {
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
                if (IOSDriverManager.isIOS) {
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
                if (IOSDriverManager.isIOS) {
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
                if (IOSDriverManager.isIOS) {
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

    void customHorizontalSwipe(final int elementCentreY, String direction) {
        int startX = deviceWidth;
        int endX = deviceWidth;
        int y = deviceHeight;

        switch (direction) {
            case LEFT:
                if (IOSDriverManager.isIOS) {
                    int startLeftX = (int) (startX * 0.8);
                    int startLeftY = (int) (y - (elementCentreY * 0.5));
                    int vectorLeftX = (int) (endX * 0.2 - startLeftX);
                    int vectorLeftY = (int) (y - (elementCentreY * 0.5 - startLeftY));

                    swipe(startLeftX, startLeftY, vectorLeftX, vectorLeftY);
                } else {
                    swipe((int) (startX * 0.8), (int) (y - (elementCentreY * 0.5)), (int) (endX * 0.2), (int) (y - (elementCentreY * 0.5)));
                }

            case RIGHT:
                if (IOSDriverManager.isIOS) {
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