package core.constants;

import core.managers.DisplayManager;
import io.appium.java_client.AppiumDriver;

public class SwipeDirection {

    private DisplayManager displayManager;

    public SwipeDirection(AppiumDriver driver) {
        displayManager = new DisplayManager(driver);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * int[] Array: StartX, StartY, EndX, EndY
     *
     * @param direction enum representing Swipe direction
     * @return int[] array
     */
    public int[] getDirection(Direction direction) {
        final int deviceHeight = displayManager.getDeviceHeight();
        final int deviceWidth = displayManager.getDeviceWidth();
        final int[] dir = new int[4];

        switch (direction) {
            case UP:
                dir[0] = deviceWidth / 2;
                dir[1] = (int) (deviceHeight * 0.3);
                dir[2] = deviceWidth / 2;
                dir[3] = (int) (deviceHeight * 0.8);
                return dir;

            case DOWN:
                dir[0] = deviceWidth / 2;
                dir[1] = (int) (deviceHeight * 0.8);
                dir[2] = deviceWidth / 2;
                dir[3] = (int) (deviceHeight * 0.3);
                return dir;

            case LEFT:
                dir[0] = (int) (deviceWidth * 0.8);
                dir[1] = deviceHeight / 2;
                dir[2] = (int) (deviceWidth * 0.1);
                dir[3] = deviceHeight / 2;
                return dir;

            case RIGHT:
                dir[0] = (int) (deviceWidth * 0.1);
                dir[1] = deviceHeight / 2;
                dir[2] = (int) (deviceWidth * 0.8);
                dir[3] = deviceHeight / 2;
                return dir;
        }
        return null;
    }
}
