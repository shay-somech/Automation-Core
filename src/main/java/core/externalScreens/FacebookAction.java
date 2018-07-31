package core.externalScreens;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.utils.FunctionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FacebookAction extends FacebookConstants {

    private static FacebookAction instance;

    public static FacebookAction getInstance() {
        if (instance == null)
            instance = new FacebookAction();
        return instance;
    }

    private void loginToFacebookApp(String email, String password) {

        List<WebElement> elements = DriverManager.getDriver().findElements(By.xpath(FacebookApp.Xpath.LOGIN_SCREEN_TEXT_FIELD));

        for (int i = 0; i < elements.size(); i++) {

            switch (i) {
                /** @case 0 represents Email or Username text field inside Facebook app */
                case 0:
                    elements.get(i).sendKeys(email);
                    break;
                /** @case 1 represents Password text field inside Facebook app */
                case 1:
                    elements.get(i).sendKeys(password);
                    break;
                default:
                    MyLogger.logSys("Can't locate Facebook elements");
                    break;
            }
        }
    }


    private void facebookShare() {
        MyLogger.logSys("Facebook user is logged in... Posting to Facebook");
        FunctionHelper.wait(3);
        getElementByText(Text.WRITE_SOMETHING_TEXT).findAndReturn().sendKeys("Automation Test");
        if (getElementByText(Text.POST_BUTTON).findAndReturn().isExistAndDisplayed()) {
            getElementByText(Text.POST_BUTTON).findAndClick();
        } else getElementByText(Text.POST_BUTTON_UPPER_CASE).findAndClick();
    }


    private void loginIfUserConnected(String password) {

        MyLogger.logSys("Facebook user (Gini Tst) is connected but not Logged In!");
        getElementByText("Gini Tst").findAndClick();
        getElementByXpath(FacebookApp.Xpath.LOGIN_SCREEN_TEXT_FIELD).findAndReturn().sendKeys(password);
        getElementByText(FacebookApp.LOGIN_SCREEN_LOGIN_BUTTON).findAndClick();
    }

    private void fullUserLogin(String username, String password) {

        MyLogger.logSys("Facebook user is NOT connected! Facebook App identified... Connecting to Facebook");
        loginToFacebookApp(username, password);
        getElementByText(FacebookApp.LOGIN_SCREEN_LOGIN_BUTTON).findAndClick();
    }


    public void loginToFacebookAndShare(String username, String password) {

        if (getElementByText(FacebookApp.LOGIN_SCREEN_TAP_TO_LOGIN).find(false)) {
            MyLogger.logSys("Facebook user is connected but not Logged In! Logging In then tapping on Share Button");

            loginIfUserConnected(password);
            facebookShare();

        } else if (getElementByText(FacebookApp.LOGIN_SCREEN_EMAIL_OR_PHONE_TEXT_FIELD).find(false)) {

            fullUserLogin(username, password);
            facebookShare();

        } else if (getElementByText(Text.FACEBOOK).find(false) && getElementByText(Text.POSTING_TO).find(false)) {

            facebookShare();

        } else {
            throw new AssertionError("Cannot connect to Facebook!");
        }
    }
}