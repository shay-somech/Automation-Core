import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Testing {

    private AppiumDriver driver;

    @BeforeTest
    private void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "AndroidDevice");
        caps.setCapability("noReset", true);
        caps.setCapability("appPackage", "tv.i24news.i24news");
        caps.setCapability("appActivity", "tv.i24news.activities.LauncherActivity");
        caps.setCapability("platformName", "android");
        caps.setCapability("skipDeviceInitialization", true);

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void test01() {

    }

    @AfterTest
    private void tearDown() {
        driver.quit();
    }

}

