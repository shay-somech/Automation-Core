package core.utils;

import core.baseclasses.ElementFinder.FindBy;

import static core.baseclasses.ElementFinder.findElementBy;

public class DialogHelper {

    public static class ID {
        static final String ALLOW_PUSH_NOTIFICATIONS_BUTTON = "permission_allow_button";
        static final String DENY_PUSH_NOTIFICATIONS_BUTTON = "permission_deny_button";
        static final String PUSH_NOTIFICATION_TIME_STAMP = "smallNotificationTime";
        static final String PUSH_NOTIFICATION_TITLE = "smallNotificationTitle";
        static final String PUSH_NOTIFICATION_IMAGE = "smallNotificationImage";
    }

    public void clickAllowPushNotificationsButton() {
        findElementBy(FindBy.ID, ID.ALLOW_PUSH_NOTIFICATIONS_BUTTON).findAndClick();
    }

    public void clickDenyPushNotificationsButton() {
        findElementBy(FindBy.ID, ID.DENY_PUSH_NOTIFICATIONS_BUTTON).findAndClick();
    }
}
