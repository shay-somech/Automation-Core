package core.utils;

import core.baseclasses.ElementFinder;
import core.baseclasses.ElementFinder.FindBy;
import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ElementWrapper implements WebElement {

    private WebElement locator;
    private By selector;
    private String xpath;
    private int timeout = 5;

    public ElementWrapper(By by) {
        this(by, true, false);
    }

    private ElementWrapper(By by, boolean shouldFailTestIfNotFound, boolean find) {
        this.selector = by;

        if (find)
            find(timeout, shouldFailTestIfNotFound);
    }

    public ElementWrapper(String xpath) {
        this(xpath, true, false);
    }

    public ElementWrapper(String xpath, boolean onScreen) {
        this(xpath, onScreen, false);
    }

    private ElementWrapper(String xpath, boolean onScreen, boolean find) {
//        this.xpath = getOnScreenXpath(xpath, onScreen);
        this.xpath = xpath;

        if (find)
            find();
    }

    private String getOnScreenXpath(String xpath, boolean onScreen) {
        return xpath + XpathGenerator.getOnScreenParam(onScreen);
    }

    void find() {
        find(timeout, true);
    }

    boolean find(int timeOut, boolean shouldFailTestIfNotFound) {
        try {
            this.locator = new WebDriverWait(DriverManager.getDriver(), timeOut).until(ExpectedConditions.presenceOfElementLocated(getSelector()));
            MyLogger.logSys("found desired Element : " + getSelector().toString());
            return true;

        } catch (WebDriverException e) {
            if (shouldFailTestIfNotFound) {
                throw new AssertionError("Unable to find Element within timeout : " + e.getMessage());
            } else {
                this.locator = null;
                MyLogger.logSys("Unable to find element (" + getSelector().toString() + ") within timeout of : " + timeOut + " seconds");
            }
            return false;
        }
    }

    public ElementWrapper findElementBy(FindBy by, String element) {
        ElementFinder.findElementBy(by, element);
        return this;
    }

    public static List findElements(FindBy findBy, String element) {
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

    public ElementWrapper findAndReturn() {
        find(timeout, false);
        return this;
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

    public String getElementParent() {
        return xpath + "/parent::*";
    }

    public boolean isSelected(boolean selected) {
        if (selected) {
            return isSelected();
        } else {
            return false;
        }
    }

    public boolean isChecked() {
        return getAttribute("checked").equals("true");
    }

    public void verifyChecked() {
        if (!isChecked())
            throw new AssertionError("Element is not checked");
    }

    public String getXpath() {
        return xpath;
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
            throw new AssertionError("Unable to verify  :" + getXpath());
    }

    public boolean isExistAndDisplayed() {
        boolean existAndDisplayed = locator != null && isDisplayed();
        MyLogger.logSys("isExistAndDisplayed called for Element : " + getSelector().toString() + " and found ? " + existAndDisplayed);
        return locator != null && isDisplayed();
    }

    public boolean isHidden() {
        return getAttribute("hidden").equals("true");
    }

    int getElementY() {
        return getRect().getY();
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
        return locator.getScreenshotAs(target);
    }
}
