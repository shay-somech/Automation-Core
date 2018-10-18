package core.externalScreens;

import core.utils.Log;
import core.utils.FunctionHelper;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.baseclasses.ElementFinder.findElementBy;
import static core.baseclasses.ElementFinder.findElementsBy;
import static core.constants.FindByLocator.TEXT;
import static core.constants.FindByLocator.XPATH;

public class FacebookAction extends FacebookConstants {

    private static FacebookAction instance;

    public static FacebookAction getInstance() {
        if (instance == null)
            instance = new FacebookAction();
        return instance;
    }

    private void loginToFacebookApp(String email, String password) {

        List elements = findElementsBy(XPATH, FacebookApp.Xpath.LOGIN_SCREEN_TEXT_FIELD);

        for (int i = 0; i < elements.size(); i++) {

            switch (i) {
                /** @case 0 represents Email or Username text field inside Facebook app */
                case 0:
                    WebElement emailTextField = (WebElement) elements.get(i);
                    emailTextField.sendKeys(email);
                    break;
                /** @case 1 represents Password text field inside Facebook app */
                case 1:
                    WebElement passwordField = (WebElement) elements.get(i);
                    passwordField.sendKeys(password);
                    break;
                default:
                    Log.info("Can't locate Facebook elements");
                    break;
            }
        }
    }


    private void facebookShare() {
        Log.info("Facebook user is logged in... Posting to Facebook");
        FunctionHelper.wait(3);
        findElementBy(TEXT, Text.WRITE_SOMETHING_TEXT).findAndReturn().sendKeys("Automation Test");
        if (findElementBy(TEXT, Text.POST_BUTTON).findAndReturn().isExistAndDisplayed()) {
            findElementBy(TEXT, Text.POST_BUTTON).findAndClick();
        } else findElementBy(TEXT, Text.POST_BUTTON_UPPER_CASE).findAndClick();
    }


    private void loginIfUserConnected(String password) {

        Log.info("Facebook user (Gini Tst) is connected but not Logged In!");
        findElementBy(TEXT, "Gini Tst").findAndClick();
        findElementBy(XPATH, FacebookApp.Xpath.LOGIN_SCREEN_TEXT_FIELD).findAndReturn().sendKeys(password);
        findElementBy(TEXT, FacebookApp.LOGIN_SCREEN_LOGIN_BUTTON).findAndClick();
    }

    private void fullUserLogin(String username, String password) {

        Log.info("Facebook user is NOT connected! Facebook App identified... Connecting to Facebook");
        loginToFacebookApp(username, password);
        findElementBy(TEXT, FacebookApp.LOGIN_SCREEN_LOGIN_BUTTON).findAndClick();
    }


    public void loginToFacebookAndShare(String username, String password) {

        if (findElementBy(TEXT, FacebookApp.LOGIN_SCREEN_TAP_TO_LOGIN).findAndReturn().isExistAndDisplayed()) {
            Log.info("Facebook user is connected but not Logged In! Logging In then tapping on Share Button");

            loginIfUserConnected(password);
            facebookShare();

        } else if (findElementBy(TEXT, FacebookApp.LOGIN_SCREEN_EMAIL_OR_PHONE_TEXT_FIELD).findAndReturn().isExistAndDisplayed()) {

            fullUserLogin(username, password);
            facebookShare();

        } else if (findElementBy(TEXT, Text.FACEBOOK).findAndReturn().isExistAndDisplayed() && findElementBy(TEXT, Text.POSTING_TO).findAndReturn().isExistAndDisplayed()) {

            facebookShare();

        } else {
            throw new AssertionError("Cannot connect to Facebook!");
        }
    }
}