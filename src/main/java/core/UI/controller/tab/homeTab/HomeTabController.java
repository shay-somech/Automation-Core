package core.UI.controller.tab.homeTab;

import core.UI.application.UiSelection;
import core.constants.PlatformType;
import core.utils.IOSHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeTabController implements Initializable {

    private UiSelection uiSelection;

    @FXML
    public CheckBox noReset;
    @FXML
    private ComboBox<PlatformType> platformComboBox, secondPlatformComboBox;
    @FXML
    private ComboBox<String> deviceComboBox, deviceComboBox2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiSelection = UiSelection.getInstance();
        setDevicesComboBoxes();
    }

    private void setDevicesComboBoxes() {
        onDeviceComboBoxSelection(platformComboBox, deviceComboBox);
        onDeviceComboBoxSelection(secondPlatformComboBox, deviceComboBox2);
    }

    private void onDeviceComboBoxSelection(ComboBox<PlatformType> platformCB, ComboBox<String> deviceCB) {
        platformCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                deviceCB.getItems().clear();
            }

            if (newValue != null) {
                switch (newValue) {
                    case ANDROID:
//                        deviceCB.getItems().addAll(AndroidHelper.getAndroidDeviceWithDetails());
                        break;

                    case IOS:
                        deviceCB.getItems().addAll(IOSHelper.getIOSDevicesWithDetails());
                        break;
                }
            }
        });
    }

    public void setSelections() {
        uiSelection.setPlatform(platformComboBox.getValue());
        uiSelection.setSecondPlatform(secondPlatformComboBox.getValue());

        if (deviceComboBox.getValue() != null) {
            uiSelection.setDevice(deviceComboBox.getValue().substring(deviceComboBox.getValue().indexOf("||") + 3));
        }

        if (deviceComboBox2.getValue() != null) {
            uiSelection.setSecondDevice(deviceComboBox2.getValue().substring(deviceComboBox2.getValue().indexOf("||") + 3));
        }

        uiSelection.setNoReset(noReset.isSelected());
    }

    public boolean checkForDuplicateDevicesSelection() {
        return deviceComboBox.getValue().equals(deviceComboBox2.getValue());
    }
}