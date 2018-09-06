package core.externalScreens;

import static core.baseclasses.ElementFinder.FindBy.ID;
import static core.baseclasses.ElementFinder.findElementBy;

public class WhatsappAction extends WhatsappConstants {

    private static WhatsappAction instance;

    public static WhatsappAction getInstance() {
        if (instance == null)
            instance = new WhatsappAction();
        return instance;
    }


    // TODO: COMPLETE THIS METHOD
    public void shareViaWhatsappNativeOption() {
        findElementBy(ID, Id.NATIVE_WHATSAPP_SHARE).findAndClick();
    }
}
