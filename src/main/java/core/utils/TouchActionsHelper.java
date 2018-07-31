package core.utils;

import core.managers.drivers.DriverManager;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TouchActionsHelper {

    private static Actions action = new Actions(DriverManager.getDriver());
    private static TouchAction touchAction = new TouchAction(DriverManager.getDriver());

    public static void tap(int x, int y) {
        touchAction.press(PointOption.point(x, y)).release().perform();
    }

    public static void pressMoveElementToOffset(WebElement element, int xOffset, int yOffset) {
        touchAction.press(ElementOption.element(element)).moveTo(PointOption.point(xOffset, yOffset)).release().perform();
    }

    public static void doubleClickElement(ElementWrapper elementWrapper) {
        action.doubleClick(elementWrapper).perform();
    }

    public static void dragAndDropElement(WebElement element, int xOffset, int yOffset) {
        action.dragAndDropBy(element, xOffset, yOffset).perform();
    }

    public static void clickAndHoldElement(ElementWrapper elementWrapper) {
        action.clickAndHold(elementWrapper).perform();
    }

    public static void longPressElement(WebElement element) {
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element))).release().perform();
    }

    public static void longPressPoint(int x, int y) {
        touchAction.longPress(LongPressOptions.longPressOptions().withPosition(PointOption.point(x, y))).perform();
    }
}
