package core.utils;


import static core.baseclasses.ElementFinder.findElementBy;
import static core.constants.FindByLocator.ID;

public class DialogHelper {

    public static class Id {
        static final String ALLOW_PUSH_NOTIFICATIONS_BUTTON = "permission_allow_button";
        static final String DENY_PUSH_NOTIFICATIONS_BUTTON = "permission_deny_button";
        static final String PUSH_NOTIFICATION_TIME_STAMP = "smallNotificationTime";
        static final String PUSH_NOTIFICATION_TITLE = "smallNotificationTitle";
        static final String PUSH_NOTIFICATION_IMAGE = "smallNotificationImage";
    }

    public void clickAllowPushNotificationsButton() {
        findElementBy(ID, Id.ALLOW_PUSH_NOTIFICATIONS_BUTTON).findAndClick();
    }

    public void clickDenyPushNotificationsButton() {
        findElementBy(ID, Id.DENY_PUSH_NOTIFICATIONS_BUTTON).findAndClick();
    }
}
