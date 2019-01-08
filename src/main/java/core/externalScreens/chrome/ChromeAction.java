package core.externalScreens.chrome;

import core.constants.KeyboardEvents;
import core.utils.ActionHelper;
import core.utils.Log;
import io.appium.java_client.AppiumDriver;

import static core.utils.ElementWrapper.waitForElementToAppear;

public class ChromeAction extends ChromeEntities {

    // TODO: 2019-01-01 Need to be converted to ChromeDriver
    private AppiumDriver driver;

    public ChromeAction(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * ANDROID ONLY
     * This method launches Chrome browser then searches via Google for search result and finally uses Deep Link functionality in order to launch App
     */
    public void searchForGoogleResultsInsideChrome(String searchFor) {
        String url = "www.google.co.il";
        Log.info("Launching " + url + " Website");
        launchWebsiteInsideChrome(url);
        searchInsideGoogleChrome(searchFor);
    }

    private void launchWebsiteInsideChrome(String url) {
        driver.get(url);
    }

    private void searchInsideGoogleChrome(String searchFor) {
        Log.info("Searching Google for " + searchFor);
        googleSearchBar.sendKeys(searchFor);
        googleSearchButton.click();
        ActionHelper.getInstance().wait(3);
        ActionHelper.getInstance().sendKeyboardEvent(KeyboardEvents.SEARCH);
    }
}
