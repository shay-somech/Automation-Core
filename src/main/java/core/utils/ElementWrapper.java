package core.utils;

import core.constants.FindByLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static core.managers.drivers.DriverManager.webDriverWait;

public class ElementWrapper {

    public static List<WebElement> waitForAllElementsToAppear(List<WebElement> elements, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static List<WebElement> waitForAllElementsToAppear(FindByLocator by, String element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElements((WebElement) by.getLocator(element)));
    }

    public static WebElement waitForElementToAppear(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementToAppear(FindByLocator by, String element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOf((WebElement) by.getLocator(element)));
    }

    public static boolean waitForInvisibilityOfAllElements(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public static boolean waitForInvisibilityOfElement(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitForInvisibilityOfElement(FindByLocator by, String element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOf((WebElement) by.getLocator(element)));
    }
}
