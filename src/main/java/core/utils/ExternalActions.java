package core.utils;

import core.externalScreens.chrome.ChromeAction;
import core.externalScreens.facebook.FacebookAction;
import core.externalScreens.gmail.GmailAction;
import core.externalScreens.iosMail.IOSMailAction;
import core.externalScreens.whatsapp.WhatsappAction;
import core.managers.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;

public class ExternalActions {

    private static AppiumDriver driver;
    private ChromeAction chromeAction;
    private FacebookAction facebookAction;
    private GmailAction gmailAction;
    private WhatsappAction whatsappAction;
    private IOSMailAction iosMailAction;
    private static ExternalActions instance;

    private ExternalActions() {
        driver = DriverManager.getDriver();
        chromeAction = new ChromeAction(driver);
        facebookAction = new FacebookAction();
        gmailAction = new GmailAction();
        whatsappAction = new WhatsappAction();
        iosMailAction = new IOSMailAction();
    }

    public static ExternalActions getInstance() {
        if (instance == null)
            instance = new ExternalActions();
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

    public void shareViaIOSEmail(String recipient) {
        iosMailAction.sendIOSEmail(recipient);
    }

    public void shareViaFacebook(String username, String password) {
        facebookAction.loginToFacebookAndShare(username, password);
    }
}
