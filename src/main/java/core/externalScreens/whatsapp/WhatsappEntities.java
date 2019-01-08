package core.externalScreens.whatsapp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.externalScreens.whatsapp.WhatsappEntities.Id.NATIVE_WHATSAPP_SHARE;

class WhatsappEntities {

    static class Id {
        static final String NATIVE_WHATSAPP_SHARE = "shareBarWhatsappIv";
    }

    @FindBy(id = NATIVE_WHATSAPP_SHARE)
    WebElement nativeWhatsappShareButton;
}
