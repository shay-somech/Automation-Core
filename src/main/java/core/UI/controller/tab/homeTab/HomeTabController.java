package core.UI.controller.tab.homeTab;

import core.constants.PlatformType;
import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static core.UI.controller.main.MainView.UiSelections.*;
import static core.UI.controller.main.MainView.uiSelection;


public class HomeTabController implements HomeTabContract.View, Initializable {

    @FXML
    private VBox vBox;
    @FXML
    public CheckBox noReset;
    @FXML
    public ComboBox<PlatformType> platformComboBox;
    @FXML
    public ComboBox<String> deviceComboBox;
    @FXML
    public Button runButton;

    private HomeTabPresenter homePresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePresenter = new HomeTabPresenter(this);
        homePresenter.onPlatformSelection();

        vBox.addEventHandler(KeyEvent.KEY_PRESSED, event -> homePresenter.handleKeyBoardShortcuts(event));
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

    // TODO: 22/10/2019 Bug! - Button needs to be pressed twice in order to be closed
    public void onRunButtonClicked(ActionEvent event) {
        String deviceWithDetails = deviceComboBox.getValue();
        String deviceId = deviceWithDetails.substring(deviceWithDetails.indexOf("||") + 3);

        uiSelection.put(PLATFORM, platformComboBox.getValue());
        uiSelection.put(DEVICE, deviceId);
        uiSelection.put(NO_RESET, noReset.isSelected());

        runButton.setOnAction(closeEvent -> {
            Stage stage = (Stage) vBox.getScene().getWindow();
            stage.close();
        });
    }
}