package core.utils;

import core.baseclasses.BaseScreen;
import core.managers.drivers.AndroidDriverManager;

public class WebviewHelper extends BaseScreen {

    private static WebviewHelper instance;

    public static WebviewHelper getInstance() {
        if (instance == null)
            instance = new WebviewHelper();
        return instance;
    }

    private static final String WEB_VIEW_BACK_BUTTON = "browserBackButton";

    void clickWebviewBackButton() {
        if (AndroidDriverManager.isAndroid) {
            getElementById(WEB_VIEW_BACK_BUTTON).findAndClick();
        }
    }
}
