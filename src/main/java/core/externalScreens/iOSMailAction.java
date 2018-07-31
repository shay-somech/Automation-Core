package core.externalScreens;

import core.externalScreens.iOSMailConstants.ElementByAccessibilityLabel;
import core.externalScreens.iOSMailConstants.ElementsByText;
import core.utils.ActionHelper;

import static core.constants.ZoneType.INSTRUMENTED_APP;
import static core.constants.ZoneType.NATIVE;
import static core.managers.baseclasses.ElementFinder.getElementByAccessibilityLabel;
import static core.managers.baseclasses.ElementFinder.getElementByText;

public class iOSMailAction {

    public static void clickSendEmailButton() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        getElementByText(ElementsByText.SEND_MAIL_BUTTON).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    public static void clickCancelEmailButton() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        getElementByText(ElementsByText.CANCEL_MAIL_BUTTON).findAndClick();
        getElementByText(ElementsByText.DELETE_DRAFT).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    private static void clickEmailBody() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        getElementByText(ElementsByText.EMAIL_BODY).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    private static void clickSubjectTextField() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        getElementByAccessibilityLabel(ElementByAccessibilityLabel.SUBJECT_TEXT_FIELD).findAndClick();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP);
    }

    private static void clickEmailToTextField() {
        ActionHelper.getInstance().setAppContext(NATIVE);
        getElementByAccessibilityLabel(ElementByAccessibilityLabel.EMAIL_TO_TEXT_FIELD).findAndClick();
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
