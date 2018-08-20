package core.utils;

import core.baseclasses.BaseScreen;

public class DialogHelper extends BaseScreen {

    public static class ID {
        static final String ALLOW_PUSH_NOTIFICATIONS_BUTTON = "permission_allow_button";
        static final String DENY_PUSH_NOTIFICATIONS_BUTTON = "permission_deny_button";
        static final String PUSH_NOTIFICATION_TIME_STAMP = "smallNotificationTime";
        static final String PUSH_NOTIFICATION_TITLE = "smallNotificationTitle";
        static final String PUSH_NOTIFICATION_IMAGE = "smallNotificationImage";
    }

    public void clickAllowPushNotificationsButton() {
        getElementById(ID.ALLOW_PUSH_NOTIFICATIONS_BUTTON).findAndClick();
    }

    public void clickDenyPushNotificationsButton() {
        getElementById(ID.DENY_PUSH_NOTIFICATIONS_BUTTON).findAndClick();
    }
}
