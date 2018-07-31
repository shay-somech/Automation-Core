package core.externalScreens;

import core.managers.MyLogger;
import core.utils.FunctionHelper;

public class GmailAction extends GmailConstants {

    private static GmailAction instance;

    public static GmailAction getInstance() {
        if (instance == null)
            instance = new GmailAction();
        return instance;
    }

    public void shareViaGmailOption(String emailTo) {
        MyLogger.logSys("Sharing content via Native Gmail");
        try {
            if (getElementByText(Text.GMAIL).findAndReturn().isExistAndDisplayed()) {
                getElementByText(Text.GMAIL).findAndClick();
            }
            FunctionHelper.wait(3);
            // TODO: 30/07/2018 find the Element and then pass .sendKeys("") to him

            getElementById(Id.SEND_BUTTON).findAndClick();
        } catch (Exception e) {
            throw new AssertionError("Can't share via Gmail, element absent or blocked");
        }
    }
}
