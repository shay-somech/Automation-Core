package core.externalScreens;

import core.managers.MyLogger;
import core.managers.drivers.DriverManager;
import core.utils.ActionHelper;

import static core.baseclasses.ElementFinder.FindBy.XPATH;
import static core.baseclasses.ElementFinder.findElementBy;

public class ChromeAction extends ChromeConstants {

    private static ChromeAction instance;

    public static ChromeAction getInstance() {
        if (instance == null)
            instance = new ChromeAction();
        return instance;
    }

    /**
     * ANDROID ONLY
     * This method launches Chrome browser then searches via Google for search result and finally uses Deep Link functionality in order to launch App
     */

    public void searchForGoogleResultsInsideChrome(String searchFor) {
        String url = "www.google.co.il";
        MyLogger.logSys("Launching " + url + " Website");
        launchWebsiteInsideChrome(url);
        searchInsideGoogleChrome(searchFor);
    }

    private void launchWebsiteInsideChrome(String url) {
        DriverManager.getDriver().get(url);
    }

    private void searchInsideGoogleChrome(String searchFor) {
        MyLogger.logSys("Searching Google for " + searchFor);
        findElementBy(XPATH, GOOGLE_SEARCH_BAR).findAndReturn().sendKeys(searchFor);
        findElementBy(XPATH, GOOGLE_SEARCH_BUTTON).findAndClick();
        ActionHelper.getInstance().wait(3);
//        DriverManager.getAndroidDriver().pressKeyCode(66);  // Clicks Keyboard 'Enter' Key
    }
}
