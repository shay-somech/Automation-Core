package core.managers.drivers;

import core.configurationPopUp.DropDownExecutioner;
import core.managers.MyLogger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static core.constants.AppInfo.androidAppMainActivity;
import static core.constants.AppInfo.androidAppPackage;

public class AndroidDriverManager {

    public static boolean isAndroid = false;
    static final String url = "http://localhost:4723/wd/hub";
    private static AndroidDriverManager instance;

    public static AndroidDriverManager getInstance() {
        if (instance == null)
            instance = new AndroidDriverManager();
        return instance;
    }

    private DesiredCapabilities setCapabilities() {
        DesiredCapabilities dc = DropDownExecutioner.dc;

        if (dc == null) {
            dc = new DesiredCapabilities();
        }

        dc.setCapability("reportDirectory", "reports");
        dc.setCapability("reportFormat", "xml");
        dc.setCapability("testName", "Gini-Apps Automation");
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("platformName", "Android");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, androidAppPackage);
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, androidAppMainActivity);
        return dc;
    }


    public void startDriver() {
        try {
            DriverManager.setDriver(new AndroidDriver<>(new URL(url), setCapabilities()));
            isAndroid = true;

        } catch (SessionNotCreatedException e) {
            MyLogger.logSys("Android device status :: NOT READY");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
