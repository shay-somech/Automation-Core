package core.constants;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AppiumServerArgs implements ServerArgument {

    TEMP_DIRECTORY("--tmp"),
    TIMEOUT("--command-timeout"),
    LOCAL_TIME_ZONE("--local-timezone"),
    LOG_TIMESTAMP("--log-timestamp"),
    LOG_LEVEL("--log-level"),
    RELAXED_SECURITY("--relaxed-security"),
    IOS_INSTRUMENTS_LAUNCH_RETRIES("-r, --backend-retries");


    private final String arg;

    public static final String WARNING = "warn";
    public static final String WARNING_INFO = "warn:info";
    public static final String WARNING_ERROR = "warn:error";
    public static final String INFO = "info";
    public static final String INFO_INFO = "info:info";
    public static final String INFO_DEBUG = "info:debug";
    public static final String DEBUG = "debug";
    public static final String ERROR = "error";

    AppiumServerArgs(String arg) {
        this.arg = arg;
    }

    @Override
    public String getArgument() {
        return arg;
    }
}
