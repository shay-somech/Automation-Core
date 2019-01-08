package core.externalScreens.iosMail;

import core.utils.ActionHelper;

import static core.constants.FindByLocator.ACCESSIBILITY_LABEL;
import static core.constants.FindByLocator.TEXT;
import static core.constants.ZoneType.INSTRUMENTED_APP;
import static core.constants.ZoneType.NATIVE;
import static core.utils.ElementWrapper.waitForElementToAppear;

public class IOSMailAction extends IOSMailEntities {

    private int timeout = 3;

    public void clickSendEmailButton() {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        sendEmailButton.click();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP.toString());
    }

    public void clickCancelEmailButton() {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        cancelEmailButton.click();
        deleteDraft.click();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP.toString());
    }

    private void clickEmailBody() {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        emailBody.click();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP.toString());
    }

    private void clickSubjectTextField() {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        subjectTextField.click();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP.toString());
    }

    private void clickEmailToTextField() {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        emailToTextField.click();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP.toString());
    }

    public void sendIOSEmail(String recipient) {
        ActionHelper.getInstance().setAppContext(NATIVE.toString());
        clickEmailToTextField();
        // TODO: 30/07/2018 find the element and then pass sendKeys him

        ActionHelper.getInstance().hideKeyboard();
        clickSendEmailButton();
        ActionHelper.getInstance().setAppContext(INSTRUMENTED_APP.toString());
    }
}
