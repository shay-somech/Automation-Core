package core.managers.tests;

import core.managers.MyLogger;
import core.managers.baseclasses.MainBase;
import core.managers.drivers.DriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class iOSTestManager extends MainBase {

    @BeforeMethod
    public void preTest() {
        MyLogger.logger.info("Preparing Test execution");
        List iOSDoc = DriverManager.getIosDriver().findElements(By.xpath("//*[@value='Dock']"));

        if (iOSDoc.size() > 0) {
            System.out.println("ynet App NOT in Foreground");
            DriverManager.getIosDriver().launchApp();
        }
    }

    @AfterMethod
    public void postTest() {
        try {
            DriverManager.getIosDriver().closeApp();
        } catch (ClassCastException ignored) {
        }
    }
}