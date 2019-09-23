package core.UI.controller.tab.homeTab;

import core.UI.application.UiSelection;
import core.constants.PlatformType;
import javafx.scene.input.KeyEvent;


public interface HomeContract {

    interface View {

        void setPlatformListener();

        void clearDeviceComboBoxItems();

        void setComboBoxWithAndroidDetails();

        void setComboBoxWithIOSDetails();

        void updateAndroidDeviceComboBox();

        void updateIOSDeviceComboBox();

        void updateNoResetCheckbox();

        void updateSelections(UiSelection uiSelection);
    }

    interface Presenter {

        void selectedProperty(Object oldValue, PlatformType newValue);

        void onPlatformSelection();

        void updateSelections();

        void handleKeyBoardShortcuts(KeyEvent event);
    }
}