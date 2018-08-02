package core.managers.drivers;

import core.configurationPopUp.DropDownExecutioner;
import core.managers.MyLogger;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static core.constants.AppInfo.iOSBundleId;

public class IOSDriverManager {

    public static boolean isIOS = false;
    private static IOSDriverManager instance;

    private static final String url = "http://localhost:4723/wd/hub";

    public static IOSDriverManager getInstance() {
        if (instance == null)
            instance = new IOSDriverManager();
        return instance;
    }

    private DesiredCapabilities setCapabilities() {
        DesiredCapabilities dc = DropDownExecutioner.dc;

        if (dc == null) {
            dc = new DesiredCapabilities();
        }
        dc.setCapability("instrumentApp", true);
        dc.setCapability("reportDirectory", "reports");
        dc.setCapability("reportFormat", "xml");
        dc.setCapability("testName", "Gini-Apps Automation");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, iOSBundleId);
        return dc;
    }

    public void startDriver() {
        try {
            DriverManager.setDriver(new IOSDriver<>(new URL(url), setCapabilities()));
            isIOS = true;

        } catch (SessionNotCreatedException e) {
            MyLogger.logSys("iOS device status :: NOT READY");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
