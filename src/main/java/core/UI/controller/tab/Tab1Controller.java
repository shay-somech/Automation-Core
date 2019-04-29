package core.UI.controller.tab;

import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Tab1Controller implements Initializable {

    public static String platform, platform2, device, device2;
    public static boolean isNoReset;

    @FXML
    private ComboBox<String> platformComboBox, platformComboBox2, deviceComboBox, deviceComboBox2;
    @FXML
    private CheckBox noReset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPlatformComboBoxesValues();
        setDevicesComboBox();
        setDevicesComboBox2();
    }

    private void setPlatformComboBoxesValues() {
        platformComboBox.getItems().addAll("Android", "iOS");
        platformComboBox2.getItems().addAll("Android", "iOS");
    }

    private void setDevicesComboBox() {
        setDeviceComboBoxSelection(platformComboBox, deviceComboBox);
    }

    private void setDevicesComboBox2() {
        setDeviceComboBoxSelection(platformComboBox2, deviceComboBox2);
    }

    private void setDeviceComboBoxSelection(ComboBox<String> platform, ComboBox<String> device) {
        platform.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                switch (oldValue) {
                    case "Android":
                        device.getItems().clear();
                        break;

                    case "iOS":
                        device.getItems().clear();
                        break;
                }
            }

            if (newValue != null) {
                switch (newValue) {
                    case "Android":
                        device.getItems().addAll(AndroidHelper.getAndroidDeviceWithDetails());
                        break;

                    case "iOS":
                        device.getItems().addAll(IOSHelper.getIOSDevicesWithDetails());
                        break;
                }
            }
        });
    }

    public void getSelections() {
        platform = platformComboBox.getValue();
        platform2 = platformComboBox2.getValue();

        if (deviceComboBox.getValue() != null) {
            device = deviceComboBox.getValue().substring(deviceComboBox.getValue().indexOf("||") + 3);
        }

        if (deviceComboBox2.getValue() != null) {
            device2 = deviceComboBox2.getValue().substring(deviceComboBox2.getValue().indexOf("||") + 3);
        }

        isNoReset = noReset.isSelected();
    }

    public boolean isDuplicateDevicesSet() {
        return deviceComboBox.getValue().equals(deviceComboBox2.getValue());
    }

    public String getPlatformSelection() {
        return platformComboBox.getValue();
    }

    public String getDeviceSelection() {
        return deviceComboBox.getValue();
    }
}