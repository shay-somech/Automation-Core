package core.utils;

import core.managers.MyLogger;
import core.managers.drivers.AndroidDriverManager;
import core.managers.drivers.DriverManager;
import org.openqa.selenium.ScreenOrientation;

import static core.externalScreens.iOSMailAction.sendIOSEmail;

public class ActionHelper {

    private static ActionHelper instance;

    private final SwipeHelper swipeHelper;
    private final FunctionHelper functionHelper;
    private final WebviewHelper webviewHelper;
    private final AndroidHelper androidHelper;
    private final IOSHelper iosHelper;
    private final ExternalCapabilities externalCapabilities;

    private static String currentSessionId;


    private ActionHelper() {

        MyLogger.logSys("ActionHelper created");

        // seeTest must use current driver , so we check driver is valid in case
        // we started new test and driver restarted on before and after class annotations.

        currentSessionId = DriverManager.getDriver().getSessionId().toString();

        this.swipeHelper = SwipeHelper.getInstance();
        this.functionHelper = FunctionHelper.getInstance();
        this.webviewHelper = WebviewHelper.getInstance();
        this.androidHelper = AndroidHelper.getInstance();
        this.iosHelper = IOSHelper.getInstance();
        this.externalCapabilities = ExternalCapabilities.getInstance();
    }

    public static ActionHelper getInstance() {
        if (instance == null || DriverManager.isDriverExpired(currentSessionId)) {
            instance = new ActionHelper();
        }
        return instance;
    }

    public void swipeDownUntilElementFound(ElementWrapper elementWrapper) {
        swipeHelper.swipeDownUntilElementFound(elementWrapper);
    }

    public void swipeDownUntilElementFound(ElementWrapper elementWrapper, boolean click) {
        swipeHelper.swipeDownUntilElementFound(elementWrapper, click);
    }

    public void swipeDownUntilElementFound(ElementWrapper elementWrapper, boolean failIfNotFound, boolean click) {
        swipeHelper.swipeDownUntilElementFound(elementWrapper, failIfNotFound, click);
    }

    public void swipe(String direction) {
        swipeHelper.swipe(direction);
    }

    public void swipe(int startX, int endX, int startY, int endY) {
        swipeHelper.swipe(startX, startY, endX, endY);
    }

    public void customHorizontalSwipe(ElementWrapper elementWrapper, String direction) {
        elementWrapper.find();
        swipeHelper.customHorizontalSwipe(elementWrapper.getElementY(), direction);
    }

    public void pullToRefresh() {
        swipeHelper.swipeUpOnce();
    }


    /**
     * Appium Core functionality Handlers
     */

    public void wait(int seconds) {
        FunctionHelper.wait(seconds);
    }

    public void setAppContext(String zone) {
        MyLogger.logSys("Switching App Context to :: " + zone);
        DriverManager.getDriver().context(zone);
    }

    public void closeApp() {
        functionHelper.closeApp();
    }

    public void launchApp() {
        functionHelper.launchApp();
    }

    public void changeDeviceOrientation(ScreenOrientation orientation) {
        functionHelper.changeDeviceOrientation(orientation);
    }


    /**
     * Android Functionality Handlers
     */

    public void clickAndroidBackButton() {
        androidHelper.clickAndroidBackButton();
    }

    public void openAndroidNotificationCenter() {
        androidHelper.openAndroidNotificationCenter();
    }

    public void startAndroidActivity(String appPackage, String appMainActivity) {
        androidHelper.startAndroidActivity(appPackage, appMainActivity);
    }

    public String getAndroidCurrentActivity() {
        return androidHelper.getAndroidCurrentActivity();
    }

    public void hideKeyboard() {
        if (AndroidDriverManager.isAndroid) {
            androidHelper.hideAndroidKeyboard();
        } else {
            iosHelper.closeIOSKeyboard();
        }
    }

    public void luanchAndroidSettings() {
        androidHelper.launchAndroidSettings();
    }


    /**
     * IOS Functionality handlers
     */

    public void clickIOSNativeCancelButton() {
        iosHelper.clickIOSNativeCancelButton();
    }

    public void shareViaIOSEmail(String recipient) {
        sendIOSEmail(recipient);
    }

    /**
     * External Activity Handlers
     */

    public void shareViaFacebook(String username, String password) {
        externalCapabilities.loginToFacebookAndShare(username, password);
    }

    public void shareViaGmail(String emailTo) {
        externalCapabilities.shareViaGmailNativeOption(emailTo);
    }

    public void shareViaWhatsapp() {
        externalCapabilities.shareViaWhatsappNativeOption();
    }


    /**
     * Ynet App Handlers
     */

    public void playEntireVideo(ElementWrapper videoDurationElement) {
        functionHelper.playEntireVideo(videoDurationElement);
    }

    public void clickWebviewBackButton() {
        webviewHelper.clickWebviewBackButton();
    }
}