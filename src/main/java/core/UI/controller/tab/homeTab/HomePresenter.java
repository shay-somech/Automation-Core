package core.UI.controller.tab.homeTab;

import core.UI.application.UiSelection;
import core.constants.PlatformType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private UiSelection uiSelection;

    HomePresenter(HomeContract.View view) {
        this.view = view;
        uiSelection = UiSelection.getInstance();
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
    public void updateSelections() {
        view.updateSelections(uiSelection);
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
