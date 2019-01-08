package core.externalScreens.gmail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.externalScreens.gmail.GmailEntities.Id.SEND_BUTTON;
import static core.externalScreens.gmail.GmailEntities.Text.GMAIL;

class GmailEntities {

    static final class Id {
        static final String SEND_BUTTON = "send";
    }

    static final class Text {
        static final String GMAIL = "//*[@text= 'Gmail']";
    }

    @FindBy(id = SEND_BUTTON)
    WebElement sendButton;

    @FindBy(xpath = GMAIL)
    WebElement gmailButton;
}
