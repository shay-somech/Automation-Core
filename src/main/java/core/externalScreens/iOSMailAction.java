package core.externalScreens;

import core.externalScreens.iOSMailConstants.ElementByAccessibilityLabel;
import core.externalScreens.iOSMailConstants.ElementsByText;
import core.utils.ActionHelper;

import static core.baseclasses.ElementFinder.FindBy.ACCESSIBILITY_LABEL;
import static core.baseclasses.ElementFinder.FindBy.TEXT;
import static core.baseclasses.ElementFinder.findElementBy;
import static core.constants.ZoneType.INSTRUMENTED_APP;
import static core.constants.ZoneType.NATIVE;

public class iOSMailAction {

    public static void clickSendEmailButton() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        findElementBy(TEXT, ElementsByText.SEND_MAIL_BUTTON).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    public static void clickCancelEmailButton() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        findElementBy(TEXT, ElementsByText.CANCEL_MAIL_BUTTON).findAndClick();
        findElementBy(TEXT, ElementsByText.DELETE_DRAFT).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    private static void clickEmailBody() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        findElementBy(TEXT, ElementsByText.EMAIL_BODY).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    private static void clickSubjectTextField() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        findElementBy(ACCESSIBILITY_LABEL, ElementByAccessibilityLabel.SUBJECT_TEXT_FIELD).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    private static void clickEmailToTextField() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        findElementBy(ACCESSIBILITY_LABEL, ElementByAccessibilityLabel.EMAIL_TO_TEXT_FIELD).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    public static void sendIOSEmail(String recipient) {
        ActionHelper.getInstance().setAppContext(NATIVE);
        clickEmailToTextField();
        // TODO: 30/07/2018 find the element and then pass sendKeys him

        ActionHelper.getInstance().hideKeyboard();
        clickSendEmailButton();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }
}
