package core.utils;

import core.externalScreens.ChromeAction;
import core.externalScreens.FacebookAction;
import core.externalScreens.GmailAction;
import core.externalScreens.WhatsappAction;

public class ExternalCapabilities {

    private static ExternalCapabilities instance;
    private ChromeAction chromeAction;
    private FacebookAction facebookAction;
    private GmailAction gmailAction;
    private WhatsappAction whatsappAction;
    
    public ExternalCapabilities() {
        chromeAction = ChromeAction.getInstance();
        facebookAction = FacebookAction.getInstance();
        gmailAction = GmailAction.getInstance();
        whatsappAction = WhatsappAction.getInstance();
    }

    public static ExternalCapabilities getInstance() {
        if (instance == null)
            instance = new ExternalCapabilities();
        return instance;
    }

    public void searchForGoogleResultsInsideChrome(String searchFor) {
        chromeAction.searchForGoogleResultsInsideChrome(searchFor);
    }

    void loginToFacebookAndShare(String username, String password) {
        facebookAction.loginToFacebookAndShare(username, password);
    }

    void shareViaGmailNativeOption(String emailTo) {
        gmailAction.shareViaGmailOption(emailTo);
    }

    void shareViaWhatsappNativeOption() {
        whatsappAction.shareViaWhatsappNativeOption();
    }
}
