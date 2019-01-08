package core.externalScreens.gmail;

import core.utils.ActionHelper;
import core.utils.Log;

import static core.utils.ElementWrapper.waitForElementToAppear;

public class GmailAction extends GmailEntities {

    public void shareViaGmailOption(String emailTo) {
        Log.info("Sharing content via Native Gmail");
        try {
            if (gmailButton.isDisplayed()) {
                gmailButton.click();
            }
            ActionHelper.getInstance().wait(3);
            // TODO: 30/07/2018 find the Element and then pass .sendKeys("") to him

            sendButton.click();
        } catch (Exception e) {
            throw new AssertionError("Can't share via Gmail, element absent or blocked");
        }
    }
}
