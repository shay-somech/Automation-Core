package core.UI.controller.tab.homeTab;

import core.constants.PlatformType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class HomeTabPresenter implements HomeTabContract.Presenter {

    private HomeTabContract.View view;

    HomeTabPresenter(HomeTabContract.View view) {
        this.view = view;
    }

    @Override
    public void selectedProperty(Object oldValue, PlatformType newValue) {
        if (oldValue != null) {
            view.clearDeviceComboBoxItems();
        }

        if (newValue != null) {
            switch (newValue) {
                case ANDROID:
                    view.setComboBoxWithAndroidDetails();
                    break;

                case IOS:
                    view.setComboBoxWithIOSDetails();
                    break;
            }
        }
    }

    @Override
    public void onPlatformSelection() {
        view.setPlatformListener();
    }

    @Override
    public void handleKeyBoardShortcuts(KeyEvent event) {
        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.R,
                KeyCombination.SHORTCUT_ANY);

        final KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.N,
                KeyCombination.SHORTCUT_ANY);

        final KeyCombination keyComb3 = new KeyCodeCombination(KeyCode.A,
                KeyCombination.SHORTCUT_ANY);

        final KeyCombination keyComb4 = new KeyCodeCombination(KeyCode.I,
                KeyCombination.SHORTCUT_ANY);


        if (keyComb1.match(event)) {
//            runButtonClicked();
        }


        if (keyComb2.match(event)) {
            view.updateNoResetCheckbox();
            return;
        }

        if (keyComb3.match(event)) {
            view.updateAndroidDeviceComboBox();
            return;
        }

        if (keyComb4.match(event)) {
            view.updateIOSDeviceComboBox();
        }
    }
}
