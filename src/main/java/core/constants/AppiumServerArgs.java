package core.constants;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AppiumServerArgs implements ServerArgument {

    TEMP_DIRECTORY("--tmp"),
    TIMEOUT("--command-timeout"),
    LOCAL_TIME_ZONE("--local-timezone"),
    LOG_LEVEL("--log-level");


    private final String arg;

    public static final String WARNING = "warn";
    public static final String INFO = "info";
    public static final String DEBUG = "debug";

    AppiumServerArgs(String arg) {
        this.arg = arg;
    }

    @Override
    public String getArgument() {
        return arg;
    }
}
