package core.utils;

import core.managers.DisplayManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class SwipeDirectionHandler {

    private final int deviceHeight;
    private final int deviceWidth;

    SwipeDirectionHandler(AppiumDriver driver) {
        DisplayManager displayManager = new DisplayManager(driver);
        deviceHeight = displayManager.getDeviceHeight();
        deviceWidth = displayManager.getDeviceWidth();
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public enum Position {
        START, CENTER, END
    }

    Point getStartPoints(Direction direction) {
        switch (direction) {
            case UP:
                return new Point(deviceWidth / 2, (int) (deviceHeight * 0.3));
            case DOWN:
                return new Point(deviceWidth / 2, (int) (deviceHeight * 0.8));
            case RIGHT:
                return new Point((int) (deviceWidth * 0.1), deviceHeight / 2);
            case LEFT:
                return new Point((int) (deviceWidth * 0.8), deviceHeight / 2);
            default:
                return new Point(0, 0);
        }
    }

    Point getEndPoints(Direction direction) {
        switch (direction) {
            case UP:
                return new Point(deviceWidth / 2, (int) (deviceHeight * 0.8));
            case DOWN:
                return new Point(deviceWidth / 2, (int) (deviceHeight * 0.3));
            case RIGHT:
                return new Point((int) (deviceWidth * 0.8), deviceHeight / 2);
            case LEFT:
                return new Point((int) (deviceWidth * 0.1), deviceHeight / 2);
            default:
                return new Point(0, 0);
        }
    }

    Point getElementSwipePosition(WebElement element, Position swipePosition) {
        switch (swipePosition) {
            case START:
                return new Point(element.getRect().getWidth(), element.getRect().getHeight());
            case CENTER:
                return new Point(element.getRect().getX() + (element.getRect().getWidth() / 2), element.getRect().getY() + (element.getRect().getHeight() / 2));
            case END:
                return new Point(element.getRect().getX() + (element.getRect().getWidth()), element.getRect().getY() + element.getRect().getHeight());
            default:
                return new Point(0, 0);
        }
    }
}
