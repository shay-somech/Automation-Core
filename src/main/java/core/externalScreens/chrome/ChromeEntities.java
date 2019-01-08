package core.externalScreens.chrome;

import core.managers.drivers.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChromeEntities {

    final String CHROME_PACKAGE = "com.androidRadioButton.chrome";
    final String CHROME_ACTIVITY = "com.google.androidRadioButton.apps.chrome.MainRunner";
    final String CHROME_SEARCH_BAR = "url_bar";
    final String GOOGLE_SEARCH_BAR = "//*[@name='q']";
    final String GOOGLE_SEARCH_BUTTON = "//*[@nodeName='BUTTON'][@aria-label='Google Search']";


    ChromeEntities() {
        PageFactory.initElements((new AppiumFieldDecorator(DriverManager.getDriver())), this);
    }

    @FindBy(xpath = GOOGLE_SEARCH_BAR)
    WebElement googleSearchBar;

    @FindBy(xpath = GOOGLE_SEARCH_BUTTON)
    WebElement googleSearchButton;
}
