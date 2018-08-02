package core.constants;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AppiumServerArgs implements ServerArgument {

    TIMEOUT("--command-timeout"),
    LOCAL_TIME_ZONE("--local-timezone"),
    LOG_LEVEL("--log-level");

    private final String arg;

    AppiumServerArgs(String arg) {
        this.arg = arg;
    }

    @Override
    public String getArgument() {
        return arg;
    }
}
