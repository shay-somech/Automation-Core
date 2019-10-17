package core.UI.controller.tab.homeTab;

import core.UI.controller.main.MainView;
import core.constants.PlatformType;
import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import core.utils.Log;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements HomeContract.View, Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    public CheckBox noReset;
    @FXML
    public ComboBox<PlatformType> platformComboBox;
    @FXML
    public ComboBox<String> deviceComboBox;

    private HomePresenter homePresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePresenter = new HomePresenter(this);
        homePresenter.onPlatformSelection();
        homePresenter.updateSelections();

        MainView.uiSelections.forEach((key, value) -> Log.info(value.toString()));

        anchor.addEventHandler(KeyEvent.KEY_PRESSED, event -> homePresenter.handleKeyBoardShortcuts(event));
    }

    @Override
    public void setComboBoxWithAndroidDetails() {
        AndroidHelper androidHelper = new AndroidHelper();
        deviceComboBox.getItems().addAll(androidHelper.getAndroidDeviceWithDetails());
    }

    @Override
    public void setComboBoxWithIOSDetails() {
        deviceComboBox.getItems().addAll(IOSHelper.getIOSDevicesWithDetails());
    }

    @Override
    public void setPlatformListener() {
        platformComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                homePresenter.selectedProperty(oldValue, newValue));
    }

    @Override
    public void clearDeviceComboBoxItems() {
        deviceComboBox.getItems().clear();
    }

    @Override
    public void updateAndroidDeviceComboBox() {
        platformComboBox.setValue(PlatformType.ANDROID);
        ObservableList<String> devices = deviceComboBox.getItems();

        if (devices.size() > 0) {
            deviceComboBox.setValue(devices.get(0));
        }
    }

    @Override
    public void updateIOSDeviceComboBox() {
        platformComboBox.setValue(PlatformType.IOS);
        ObservableList<String> devices = deviceComboBox.getItems();

        if (devices.size() > 0) {
            deviceComboBox.setValue(devices.get(0));
        }
    }

    @Override
    public void updateNoResetCheckbox() {
        if (noReset.isSelected()) {
            noReset.setSelected(false);
        } else {
            noReset.setSelected(true);
        }
    }

    @Override
    public void updateSelections() {
        MainView.uiSelections.put("Platform", platformComboBox.getValue());
        MainView.uiSelections.put("Device", deviceComboBox.getValue());
        MainView.uiSelections.put("NoReset", noReset.isSelected());
    }
}