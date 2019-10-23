package core.UI.controller.tab.homeTab;

import core.constants.PlatformType;
import javafx.scene.input.KeyEvent;


public interface HomeTabContract {

    interface View {

        void setPlatformListener();

        void clearDeviceComboBoxItems();

        void setComboBoxWithAndroidDetails();

        void setComboBoxWithIOSDetails();

        void updateAndroidDeviceComboBox();

        void updateIOSDeviceComboBox();

        void updateNoResetCheckbox();

    }

    interface Presenter {

        void selectedProperty(Object oldValue, PlatformType newValue);

        void onPlatformSelection();

        void handleKeyBoardShortcuts(KeyEvent event);
    }
}
