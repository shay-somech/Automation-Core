package core.utils;

import static core.constants.FindByLocator.ID;
import static core.utils.ElementWrapper.waitForElementToAppear;

public class DialogHelper {

    public static class Id {
        static final String ALLOW_PUSH_NOTIFICATIONS_BUTTON = "permission_allow_button";
        static final String DENY_PUSH_NOTIFICATIONS_BUTTON = "permission_deny_button";
        static final String PUSH_NOTIFICATION_TIME_STAMP = "smallNotificationTime";
        static final String PUSH_NOTIFICATION_TITLE = "smallNotificationTitle";
        static final String PUSH_NOTIFICATION_IMAGE = "smallNotificationImage";
    }

    public void clickAllowPushNotificationsButton() {
        waitForElementToAppear(ID, Id.ALLOW_PUSH_NOTIFICATIONS_BUTTON, 3).click();
    }

    public void clickDenyPushNotificationsButton() {
        waitForElementToAppear(ID, Id.DENY_PUSH_NOTIFICATIONS_BUTTON, 3).click();
    }
}
