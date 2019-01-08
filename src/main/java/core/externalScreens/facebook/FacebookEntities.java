package core.externalScreens.facebook;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static core.externalScreens.facebook.FacebookEntities.FacebookApp.*;
import static core.externalScreens.facebook.FacebookEntities.FacebookApp.ClassName.LOGIN_SCREEN_TEXT_FIELD;
import static core.externalScreens.facebook.FacebookEntities.Text.*;

class FacebookEntities {

    static class Text {
        static final String NATIVE_FACEBOOK_BUTTON = "//*[@text= 'Facebook']";
        static final String POST_BUTTON_UPPER_CASE = "//*[@text= 'POST']";
        static final String POST_BUTTON = "//*[@text= 'Post']";
        static final String FACEBOOK = "//*[@text= 'Facebook']";
        static final String POSTING_TO = "//*[@text= 'Posting to']";
        static final String WRITE_SOMETHING_TEXT = "//*[@contains(text(),'Write something…']";
    }

    static class FacebookApp {
        static final String LOGIN_SCREEN_EMAIL_OR_PHONE_TEXT_FIELD = "Email or Phone";
        static final String LOGIN_SCREEN_LOGIN_BUTTON = "LOG IN";
        static final String LOGIN_SCREEN_TAP_TO_LOGIN = "Tap to Log In";


        static class ClassName {
            static final String LOGIN_SCREEN_TEXT_FIELD = "//*[@class='androidRadioButton.widget.EditText']";
        }
    }

    @FindBy(xpath = NATIVE_FACEBOOK_BUTTON)
    WebElement nativeFacebookButton;

    @FindBy(xpath = POST_BUTTON_UPPER_CASE)
    WebElement postButtonUpperCase;

    @FindBy(xpath = POST_BUTTON)
    WebElement postButton;

    @FindBy(xpath = FACEBOOK)
    WebElement facebookLabel;

    @FindBy(xpath = POSTING_TO)
    WebElement postingToLabel;

    @FindBy(xpath = WRITE_SOMETHING_TEXT)
    WebElement writeSomethingLabel;

    @AndroidFindBy(className = LOGIN_SCREEN_TEXT_FIELD)
    List<WebElement> loginScreenTextFields;

    @FindBy(id = LOGIN_SCREEN_LOGIN_BUTTON)
    WebElement loginScreenLoginButton;

    @FindBy(id = LOGIN_SCREEN_EMAIL_OR_PHONE_TEXT_FIELD)
    WebElement loginScreenEmailOrPhoneTextField;

    @FindBy(id = LOGIN_SCREEN_TAP_TO_LOGIN)
    WebElement loginScreenTapToLogin;
}
