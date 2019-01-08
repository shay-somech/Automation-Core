package core.externalScreens.facebook;

import core.utils.ActionHelper;
import core.utils.Log;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.constants.FindByLocator.TEXT;
import static core.utils.ElementWrapper.waitForElementToAppear;

public class FacebookAction extends FacebookEntities {

    private void loginToFacebookApp(String email, String password) {
        List<WebElement> textFields = loginScreenTextFields;

        for (int i = 0; i < textFields.size(); i++) {
            switch (i) {
                /** @case 0 represents Email or Username text field inside Facebook app */
                case 0:
                    textFields.get(i).sendKeys(email);
                    break;

                /** @case 1 represents Password text field inside Facebook app */
                case 1:
                    textFields.get(i).sendKeys(password);
                    break;

                default:
                    Log.info("Can't locate Facebook Fields");
                    break;
            }
        }
    }


    private void facebookShare() {
        Log.info("Facebook user is logged in... Posting to Facebook");
        ActionHelper.getInstance().wait(3);
        writeSomethingLabel.sendKeys("Automation Test");

        if (postButton.isDisplayed()) {
            postButton.click();

        } else postButtonUpperCase.click();
    }


    private void loginIfUserConnected(String password) {

        Log.info("Facebook user (Gini Tst) is connected but not Logged In!");
        waitForElementToAppear(TEXT, "Gini Tst", 3).click();
        loginScreenTextFields.get(1).sendKeys(password);
        loginScreenLoginButton.click();
    }

    private void fullUserLogin(String username, String password) {

        Log.info("Facebook user is NOT connected! Facebook App identified... Connecting to Facebook");
        loginToFacebookApp(username, password);
        loginScreenLoginButton.click();
    }


    public void loginToFacebookAndShare(String username, String password) {

        if (loginScreenTapToLogin.isDisplayed()) {
            Log.info("Facebook user is connected but not Logged In! Logging In then tapping on Share Button");

            loginIfUserConnected(password);
            facebookShare();

        } else if (loginScreenEmailOrPhoneTextField.isDisplayed()) {

            fullUserLogin(username, password);
            facebookShare();

        } else if (facebookLabel.isDisplayed() && postingToLabel.isDisplayed()) {
            facebookShare();

        } else {
            throw new AssertionError("Cannot connect to Facebook!");
        }
    }
}