package core.utils;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidKeyEvents {

    public enum KeyEvents {
        SEARCH,
        POWER,
        BACK,
        HOME,
        CLEAR,
        VOLUME_UP,
        VOLUME_DOWN,
        VOLUME_MUTE,
        START,
    }

    public static KeyEvent sendKeyEvent(KeyEvents key) {

        switch (key) {
            case SEARCH:
                return new KeyEvent(AndroidKey.SEARCH);

            case POWER:
                return new KeyEvent(AndroidKey.POWER);

            case BACK:
                return new KeyEvent(AndroidKey.BACK);

            case HOME:
                return new KeyEvent(AndroidKey.HOME);

            case CLEAR:
                return new KeyEvent(AndroidKey.CLEAR);

            case VOLUME_UP:
                return new KeyEvent(AndroidKey.VOLUME_UP);

            case VOLUME_DOWN:
                return new KeyEvent(AndroidKey.VOLUME_DOWN);

            case VOLUME_MUTE:
                return new KeyEvent(AndroidKey.VOLUME_MUTE);

            case START:
                return new KeyEvent(AndroidKey.BUTTON_START);
        }
        throw new RuntimeException("Android key " + key + " is not defined in scope");
    }
}
