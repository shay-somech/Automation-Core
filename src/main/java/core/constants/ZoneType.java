package core.constants;

public enum ZoneType {
    NATIVE, WEBVIEW, INSTRUMENTED_APP;

    @Override
    public String toString() {
        switch (this) {
            case WEBVIEW:
                return "WEBVIEW_1";

            case INSTRUMENTED_APP:
                return "INSTRUMENTED_APP";

            default:
                return "NATIVE";
        }
    }
}
