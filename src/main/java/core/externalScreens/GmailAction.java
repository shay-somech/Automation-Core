package core.externalScreens;

import core.utils.Log;
import core.utils.FunctionHelper;

import static core.baseclasses.ElementFinder.findElementBy;
import static core.constants.FindByLocator.ID;
import static core.constants.FindByLocator.TEXT;

public class GmailAction extends GmailConstants {

    private static GmailAction instance;

    public static GmailAction getInstance() {
        if (instance == null)
            instance = new GmailAction();
        return instance;
    }

    public void shareViaGmailOption(String emailTo) {
        Log.info("Sharing content via Native Gmail");
        try {
            if (findElementBy(TEXT, Text.GMAIL).findAndReturn().isExistAndDisplayed()) {
                findElementBy(TEXT, Text.GMAIL).findAndClick();
            }
            FunctionHelper.wait(3);
            // TODO: 30/07/2018 find the Element and then pass .sendKeys("") to him

            findElementBy(ID, Id.SEND_BUTTON).findAndClick();
        } catch (Exception e) {
            throw new AssertionError("Can't share via Gmail, element absent or blocked");
        }
    }
}
