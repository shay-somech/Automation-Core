package core.managers.tests;

import core.managers.MyLogger;
import core.managers.baseclasses.MainBase;
import core.utils.ActionHelper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AndroidTestManager extends MainBase {

    public ActionHelper actionHelper;

    @BeforeTest
    public void preTest() {
        MyLogger.logger.info("Preparing Test execution");
        System.out.println("App Current Activity = " + actionHelper.getAndroidCurrentActivity());

        if (!actionHelper.getAndroidCurrentActivity().contains("android.app.Dialog") && !actionHelper.getAndroidCurrentActivity().contains(".ui.activities.SplashActivity") &&
                !actionHelper.getAndroidCurrentActivity().contains(".ui.activities.MainActivity") && !actionHelper.getAndroidCurrentActivity().contains(".ui.activities.StoryActivity")) {
            System.out.println("ynet NOT in Foreground");
            actionHelper.launchApp();
        }
    }

    @AfterTest
    public void postTest() {
        actionHelper.closeApp();
    }
}
