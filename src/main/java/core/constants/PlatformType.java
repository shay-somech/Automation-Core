package core.constants;

public enum PlatformType {
    ANDROID, IOS;

    @Override
    public String toString() {
        switch (this) {
            case IOS:
                return "iOS";

            case ANDROID:
                return "Android";
        }
        throw new RuntimeException("Platform type is not defined in scope");
    }
}
