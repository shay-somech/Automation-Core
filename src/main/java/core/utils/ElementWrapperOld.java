package core.utils;

import core.constants.FindByLocator;
import core.managers.drivers.DriverManager;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static core.managers.drivers.DriverManager.webDriverWait;


public class ElementWrapperOld implements WebElement {

    private WebElement locator;
    private By selector;
    private String xpath;
    private int timeout = 3;

    public ElementWrapperOld(By by) {
        this(by, true, false);
    }

    private ElementWrapperOld(By by, boolean shouldFailTestIfNotFound, boolean find) {
        this.selector = by;

        if (find)
            find(timeout, shouldFailTestIfNotFound);
    }

    public ElementWrapperOld(String xpath) {
        this(xpath, true, false);
    }

    public ElementWrapperOld(String xpath, boolean onScreen) {
        this(xpath, onScreen, false);
    }

    private ElementWrapperOld(String xpath, boolean onScreen, boolean find) {
//        this.xpath = getOnScreenXpath(xpath, onScreen);
        this.xpath = xpath;

        if (find)
            find();
    }

    void find() {
        find(timeout, true);
    }

    boolean find(int timeOut, boolean shouldFailTestIfNotFound) {
        try {
            this.locator = webDriverWait(timeOut).until(ExpectedConditions.presenceOfElementLocated(getSelector()));
            Log.info("found desired Element : " + getSelector().toString());
            return true;

        } catch (WebDriverException e) {
            if (shouldFailTestIfNotFound) {
                throw new AssertionError("Unable to find Element within timeout : " + e.getMessage());
            } else {
                this.locator = null;
                Log.info("Unable to find element (" + getSelector().toString() + ") within timeout of : " + timeOut + " seconds");
            }
            return false;
        }
    }

//    public ElementWrapperOld findElementBy(FindByLocator by, String element) {
//        ElementFinder.findElementBy(by, element);
//        return this;
//    }

    public static List findElements(FindByLocator findBy, String element) {
        switch (findBy) {
            case XPATH:
                return DriverManager.getDriver().findElementsByXPath(element);

            case ID:
                return DriverManager.getDriver().findElementsById(element);

            case TEXT:
                return DriverManager.getDriver().findElementsByName(element);

            case ACCESSIBILITY_IDENTIFIER:
                return DriverManager.getDriver().findElementsByAccessibilityId(element);
        }

        throw new RuntimeException("Element Locator is not defined");
    }

    private By getSelector() {
        if (xpath != null) {
            return By.xpath(xpath);
        } else return selector;
    }

    public ElementWrapperOld findAndReturn() {
        find(timeout, false);
        return this;
    }

    public String getElementParent() {
        return xpath + "/parent::*";
    }

    public String getXpath() {
        return xpath;
    }

    public void findAndClick() {
        find(timeout, false);
        click();
    }

    public void findAndClickIfExist() {
        findAndClickIfExist(timeout);
    }

    private void findAndClickIfExist(int timeout) {
        find(timeout, false);
        clickIfExist();
    }

    private void clickIfExist() {
        if (locator != null)
            click();
    }

    public void verifyChecked() {
        if (!isChecked())
            throw new AssertionError("Element is not checked");
    }

    public void findAndVerifyDisplayed() {
        find();
        verifyObjectDisplayed();
    }

    public void verifyObjectDisplayed() {
        verifyObjectDisplayed(1);
    }

    public void verifyObjectDisplayed(int timeOut) {
        boolean verified = false;
        for (int i = 0; i < timeOut; i++) {
            if (isExistAndDisplayed()) {
                verified = true;
                i = timeOut;
            } else {
                ActionHelper.getInstance().wait(1);
            }
        }

        if (!verified)
            throw new AssertionError("Unable to verify  :" + getSelector());
    }

    public boolean isExistAndDisplayed() {
        boolean existAndDisplayed = locator != null && isDisplayed();
        Log.info("isExistAndDisplayed called for Element : " + getSelector().toString() + " and found ? " + existAndDisplayed);
        return locator != null && isDisplayed();
    }

    public boolean isChecked() {
        return getAttribute("checked").equals("true");
    }

    public boolean isSelected(boolean selected) {
        if (selected) {
            return isSelected();
        } else {
            return false;
        }
    }

    public boolean isHidden() {
        return getAttribute("hidden").equals("true");
    }

    int getElementY() {
        return getRect().getY();
    }

    public static List<WebElement> waitForAllElementsToAppear(List<WebElement> elements, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static WebElement waitForElementToAppear(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementToAppear(FindByLocator by, String element, int timeout) {
        switch (by) {
            case ID:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to appear in the given " + timeout + " seconds timeout");
                }

            case XPATH:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to appear in the given " + timeout + " seconds timeout");
                }

            case NAME:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to appear in the given " + timeout + " seconds timeout");
                }

            case ACCESSIBILITY_IDENTIFIER:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to appear in the given " + timeout + " seconds timeout");
                }
        }
        throw new RuntimeException("Locator is not defined in scope");
    }

    public static boolean waitForInvisibilityOfAllElements(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public static boolean waitForInvisibilityOfElement(WebElement element, int timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitForInvisibilityOfElement(FindByLocator by, String element, int timeout) {
        switch (by) {
            case ID:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.id(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to disappear in the given " + timeout + " seconds timeout");
                }

            case XPATH:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to disappear in the given " + timeout + " seconds timeout");
                }

            case NAME:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.name(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to disappear in the given " + timeout + " seconds timeout");
                }

            case ACCESSIBILITY_IDENTIFIER:
                try {
                    return webDriverWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(new MobileBy.ByAccessibilityId(element)));
                } catch (TimeoutException e) {
                    throw new RuntimeException("Element failed to disappear in the given " + timeout + " seconds timeout");
                }
        }
        throw new RuntimeException("Locator is not defined in scope");
    }

    // TODO: WebElement clean Override

    @Override
    public <T extends WebElement> List<T> findElements(By by) {
        return null;
    }

    @Override
    public <T extends WebElement> T findElement(By by) {
        return null;
    }

    @Override
    public void click() {
        locator.click();
    }

    @Override
    public String getText() {
        return locator.getText();
    }

    @Override
    public void submit() {
        locator.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        locator.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        locator.clear();
    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        WebElement element = locator;
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return locator.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return locator.isEnabled();
    }

    @Override
    public Point getLocation() {
        return locator.getLocation();
    }

    @Override
    public Dimension getSize() {
        return locator.getSize();
    }

    @Override
    public Rectangle getRect() {
        return locator.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return locator.getCssValue(propertyName);
    }

    @Override
    public boolean isDisplayed() {
        return locator.isDisplayed();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return DriverManager.getDriver().getScreenshotAs(target);
    }

}
