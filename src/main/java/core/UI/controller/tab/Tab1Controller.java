package core.UI.controller.tab;

import core.constants.PlatformType;
import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import core.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static core.constants.PlatformType.ANDROID;
import static core.constants.PlatformType.IOS;

public class Tab1Controller implements Initializable {

    public static final Map<String, Object> uiSelection = new HashMap<>();

    @FXML
    public CheckBox noReset;
    @FXML
    public ComboBox<PlatformType> platformComboBox, platformComboBox2;
    @FXML
    public ComboBox<String> deviceComboBox, deviceComboBox2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPlatformComboBoxesValues();
        setDevicesComboBox();
        setDevicesComboBox2();
    }


    private void setPlatformComboBoxesValues() {
        platformComboBox.getItems().addAll(ANDROID, IOS);
        platformComboBox2.getItems().addAll(ANDROID, IOS);
    }

    private void setDevicesComboBox() {
        setDeviceComboBoxSelection(platformComboBox, deviceComboBox);
    }

    private void setDevicesComboBox2() {
        setDeviceComboBoxSelection(platformComboBox2, deviceComboBox2);
    }

    private void setDeviceComboBoxSelection(ComboBox<PlatformType> platform, ComboBox<String> device) {
        platform.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                device.getItems().clear();
            }

            if (newValue != null) {
                switch (newValue) {
                    case ANDROID:
                        device.getItems().addAll(AndroidHelper.getAndroidDeviceWithDetails());
                        break;

                    case IOS:
                        device.getItems().addAll(IOSHelper.getIOSDevicesWithDetails());
                        break;
                }
            }
        });
    }

    public void getSelections() {
        uiSelection.put("Platform", platformComboBox.getValue());
        uiSelection.put("Device", deviceComboBox.getValue().substring(deviceComboBox.getValue().indexOf("||") + 3));
        uiSelection.put("NoReset", noReset.isSelected());

        for (String key : uiSelection.keySet()) {
            Log.info(key + " :: " + uiSelection.get(key));
        }
    }

    public boolean isDuplicateDevicesSet() {
        return deviceComboBox.getValue().equals(deviceComboBox2.getValue());
    }

    public PlatformType getPlatformSelection() {
        return platformComboBox.getValue();
    }

    public String getDeviceSelection() {
        return deviceComboBox.getValue();
    }
}