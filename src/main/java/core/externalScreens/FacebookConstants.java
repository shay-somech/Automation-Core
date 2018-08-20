package core.externalScreens;

import core.baseclasses.BaseScreen;

public class FacebookConstants extends BaseScreen {

    static class Id {
        static final String ARTICLE_FACEBOOK_SHARE = "shareBarFacebookIv";
    }

    static class Text {
        static final String NATIVE_FACEBOOK_BUTTON = "Facebook";
        static final String POST_BUTTON_UPPER_CASE = "POST";
        static final String POST_BUTTON = "Post";
        static final String FACEBOOK = "Facebook";
        static final String POSTING_TO = "Posting to ";
        static final String WRITE_SOMETHING_TEXT = "Write something…";
    }

    static class FacebookApp {
        static final String LOGIN_SCREEN_EMAIL_OR_PHONE_TEXT_FIELD = "Email or Phone";
        static final String LOGIN_SCREEN_LOGIN_BUTTON = "LOG IN";
        static final String LOGIN_SCREEN_TAP_TO_LOGIN = "Tap to Log In";


        static class Xpath {
            static final String LOGIN_SCREEN_TEXT_FIELD = "//*[@class='android.widget.EditText']";

            /**
             * This will ONLY work if Facebook app is instrumented
             */
            static final String LOGIN_SCREEN_PASSWORD_TEXT_FIELD = "//*[@hint='Password']";
        }

    }
}
