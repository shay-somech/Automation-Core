package core.managers.baseclasses;

import core.managers.drivers.AndroidDriverManager;
import core.managers.drivers.IOSDriverManager;

public class DeviceManager {

    public enum Platform {
        Android,
        Apple,
    }

    public static void setPlatform(Platform platform) {
        switch (platform) {
            case Android:
                AndroidDriverManager.getInstance().startDriver();
                break;

            case Apple:
                IOSDriverManager.getInstance().startDriver();
                break;
        }
    }
}