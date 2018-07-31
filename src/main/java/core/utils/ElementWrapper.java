package core.utils;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ElementWrapper implements WebElement {

    private WebElement locator;
    private String xpath;

    public ElementWrapper(String xpath) {
        this(xpath, true, false);
    }

    public ElementWrapper(String xpath, boolean onScreen) {
        this(xpath, onScreen, false);
    }

    public ElementWrapper(String xpath, boolean onScreen, boolean find) {
        this.xpath = xpath + XpathGenerator.getOnScreenParam(onScreen);

        if (find)
            find();
    }

    public ElementWrapper findAndReturn() {
        find(2, false);
        return this;
    }

    public void findAndClick() {
        find(2, false);
        click();
    }

    public void findAndClickIfExist() {
        findAndClickIfExist(2);
    }

    private void findAndClickIfExist(int timeout) {
        find(timeout, false);
        clickIfExist();
    }

    private void clickIfExist() {
        if (locator != null)
            locator.click();
    }

    void find() {
        find(2, true);
    }

    public boolean find(boolean shouldFailTestIfNotFound) {
        return find(2, shouldFailTestIfNotFound);
    }

    boolean find(int timeOut, boolean shouldFailTestIfNotFound) {
        try {
            this.locator = new WebDriverWait(DriverManager.getDriver(), timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            MyLogger.logSys("found desired UiObject: " + xpath);
            return true;

        } catch (WebDriverException e) {
            if (shouldFailTestIfNotFound) {
                throw new AssertionError("Unable to find  UiObject within timeout : " + e.getMessage());
            } else {
                this.locator = null;
                MyLogger.logSys("Unable to find element (" + xpath + ") within timeout of : " + timeOut + " seconds");
            }
            return false;
        }
    }

    public List<WebElement> findElements() {
        return DriverManager.getDriver().findElements(By.xpath(xpath));
    }

    public String getElementParent() {
        return xpath + "/parent::*";
    }

    public String isSelected(boolean selected) {
        if (selected) {
            return xpath + "[@selected = 'true']";
        } else
            return xpath + "[@selected = 'false']";
    }

    public boolean isChecked() {
        return locator.getAttribute("checked").equals("true");
    }

    public void verifyChecked() {
        if (!isChecked())
            throw new AssertionError("Element is not checked");
    }

    public String getXpath() {
        return xpath;
    }


    private String getOnScreenXpath(String xpath, boolean onScreen) {
        return xpath + XpathGenerator.getOnScreenParam(onScreen);
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
        boolean existAndDisplayed = locator != null && locator.isDisplayed();
        MyLogger.logSys("isExistAndDisplayed called for object : " + xpath + " and found ? " + existAndDisplayed);
        return locator != null && locator.isDisplayed();
    }

    public boolean isHidden() {
        return getAttribute("hidden").equals("true");
    }

    public boolean isExistAndDisplayed(boolean findObject) {

        if (findObject)
            find(false);

        return isExistAndDisplayed();
    }

    // TODO: WebElement clean Override

    @Override
    public void click() {
        locator.click();
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
        WebElement element = locator;
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return locator.isEnabled();
    }

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

    public String getText() {
        return locator.getText();
    }

    @Override
    public <T extends WebElement> List<T> findElements(By by) {
        return locator.findElements(by);
    }

    @Override
    public <T extends WebElement> T findElement(By by) {
        return findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return locator.isDisplayed();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return getScreenshotAs(target);
    }

    int getElementY() {
//        int elementHeight = getRect().getHeight();
//        return (getRect().y + (elementHeight));
        return locator.getRect().getY();
    }
}
