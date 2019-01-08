package core.constants;

/**
 * Currently supported only in Android Keyboards
 */
public enum KeyboardEvents {
    SEARCH("search"),
    NORMAL("normal"),
    UNSPECIFIC("unspecified"),
    NONE("none"),
    GO("go"),
    SEND("send"),
    NEXT("next"),
    DONE("done"),
    PREVIOUS("previous");

    private final String event;

    KeyboardEvents(String key) {
        event = key;
    }

    public String toString() {
        return this.event;
    }

    public boolean equalsName(String otherName) {
        return event.equals(otherName);
    }
}


