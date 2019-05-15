package core.utils;

import core.database.AppsDataSource;
import core.database.AppsDataSource.AndroidSystemApp;
import core.database.AppsDataSource.IOSSystemApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

class SystemApps {

    private AppiumDriver driver;
    private AndroidDriver androidDriver;
    private AppsDataSource dataSource;

    SystemApps(AppiumDriver driver, AndroidDriver androidDriver, AppsDataSource dataSource) {
        this.driver = driver;
        this.androidDriver = androidDriver;
        this.dataSource = dataSource;
    }

    void launchIOSSystemApp(IOSSystemApp app) {
        driver.activateApp(dataSource.getIOSApp(app));
    }

    void launchAndroidSystemApp(AndroidSystemApp app) {
        androidDriver.startActivity(new Activity(dataSource.getAndroidApp(app).packageId, dataSource.getAndroidApp(app).activityId));
    }
}
