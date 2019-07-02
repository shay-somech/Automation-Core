package core.utils;

import core.managers.drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static core.managers.drivers.DriverManager.webDriverWait;

public class ElementWrapper {

    public static List<WebElement> waitForAllElementsToAppear(List<WebElement> elements, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static WebElement waitForElementToAppear(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean waitForInvisibilityOfAllElements(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public static boolean waitForInvisibilityOfElement(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public static WebElement findElementByImage(String path) {
        return DriverManager.getDriver().findElementByImage(new ImageLocatorBuilder().getReferenceImageB64FromImageFile(path));
    }

    public static WebElement findElementByImageFromUrl(String path) {
        return DriverManager.getDriver().findElementByImage(new ImageLocatorBuilder().getReferenceImageB64FromImageUrl(path));
    }

    public static WebElement waitForImageToAppear(String path, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOf(findElementByImage(path)));
    }

    public static WebElement waitForImageFromUrlToAppear(String path, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOf(findElementByImageFromUrl(path)));
    }

    public static boolean waitForImageToDisappear(String path, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOf(findElementByImage(path)));
    }

    public static boolean waitForImageFromUrlToDisappear(String path, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOf(findElementByImageFromUrl(path)));
    }
}
