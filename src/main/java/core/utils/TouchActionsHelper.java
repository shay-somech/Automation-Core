package core.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

class TouchActionsHelper {

    private Actions actions;
    private TouchAction touchAction;

    TouchActionsHelper(AppiumDriver driver) {
        actions = new Actions(driver);
        touchAction = new TouchAction(driver);
    }

    void tap(int x, int y) {
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    void pressMoveElementToOffset(WebElement element, int xOffset, int yOffset) {
        touchAction.press(ElementOption.element(element)).moveTo(PointOption.point(xOffset, yOffset)).release().perform();
    }

    void doubleClickElement(WebElement webElement) {
        actions.doubleClick(webElement).perform();
    }

    void dragAndDropElement(WebElement element, int xOffset, int yOffset) {
        actions.dragAndDropBy(element, xOffset, yOffset).perform();
    }

    void clickAndHoldElement(WebElement webElement) {
        actions.clickAndHold(webElement).perform();
    }

    void longPressElement(WebElement element) {
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element))).release().perform();
    }

    void longPressPoint(int x, int y) {
        touchAction.longPress(LongPressOptions.longPressOptions().withPosition(PointOption.point(x, y))).perform();
    }
}
