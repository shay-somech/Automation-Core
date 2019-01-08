package core.externalScreens.iosMail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.externalScreens.iosMail.IOSMailEntities.ElementByAccessibilityLabel.EMAIL_TO_TEXT_FIELD;
import static core.externalScreens.iosMail.IOSMailEntities.ElementByAccessibilityLabel.SUBJECT_TEXT_FIELD;
import static core.externalScreens.iosMail.IOSMailEntities.ElementsByText.*;

class IOSMailEntities {

    static class ElementsByText {
        static final String SEND_MAIL_BUTTON = "//*[@text='שלח']";
        static final String CANCEL_MAIL_BUTTON = "//*[@text='ביטול']";
        static final String EMAIL_BODY = "//*[@text='גוף ההודעה']";
        static final String DELETE_DRAFT = "//*[@text='מחק טיוטא']";
    }

    static class ElementByAccessibilityLabel {
        static final String SUBJECT_TEXT_FIELD = "//*[@accessibilityLabel= 'subjectField']";
        static final String EMAIL_TO_TEXT_FIELD = "//*[@accessibilityLabel= 'toField']";
    }

    @FindBy(xpath = SEND_MAIL_BUTTON)
    WebElement sendEmailButton;

    @FindBy(xpath = CANCEL_MAIL_BUTTON)
    WebElement cancelEmailButton;

    @FindBy(xpath = EMAIL_BODY)
    WebElement emailBody;

    @FindBy(xpath = DELETE_DRAFT)
    WebElement deleteDraft;

    @FindBy(xpath = SUBJECT_TEXT_FIELD)
    WebElement subjectTextField;

    @FindBy(xpath = EMAIL_TO_TEXT_FIELD)
    WebElement emailToTextField;
}
