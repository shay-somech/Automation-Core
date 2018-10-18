package core.utils;

import core.managers.drivers.AndroidDriverManager;

import static core.baseclasses.ElementFinder.findElementBy;
import static core.constants.FindByLocator.ID;

public class WebviewHelper {

    private static WebviewHelper instance;

    public static WebviewHelper getInstance() {
        if (instance == null)
            instance = new WebviewHelper();
        return instance;
    }

    private static final String WEB_VIEW_BACK_BUTTON = "browserBackButton";

    void clickWebviewBackButton() {
        if (AndroidDriverManager.isAndroid) {
            findElementBy(ID, WEB_VIEW_BACK_BUTTON).findAndClick();
        }
    }
}
