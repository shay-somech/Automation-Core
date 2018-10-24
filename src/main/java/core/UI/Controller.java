package core.UI;

import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import core.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static String selectedPlatform;
    public static String selectedDevice;
    public static String selectedApp;
    public static boolean resetApp;
    public static boolean installApp;

    @FXML
    public BorderPane borderPane;
    public Button runButton;
    public ComboBox<String> deviceComboBox;
    public ComboBox<String> appComboBox;
    public ComboBox<String> platformComboBox;
    public CheckBox resetAppCheckBox;
    public CheckBox installAppCheckBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.info("Launching Automation UI ...");

        assert platformComboBox != null : "Platform ComboBox is not initialized!";
        assert deviceComboBox != null : "Device ComboBox is not initialized!";
        assert appComboBox != null : "App Path ComboBox is not initialized!";
        assert runButton != null : "Run Button is not initialized!";

        setPlatformComboBoxValues();
        setDeviceComboBox();
        setAppComboBoxValues();

        platformComboBox.setOnAction(event -> selectedPlatform = platformComboBox.getValue());
        deviceComboBox.setOnAction(event -> selectedDevice = deviceComboBox.getValue().substring(deviceComboBox.getValue().indexOf("||") + 3));
        appComboBox.setOnAction(event -> selectedApp = appComboBox.getValue());
        resetAppCheckBox.setOnAction(event -> resetApp = resetAppCheckBox.isSelected());
        installAppCheckBox.setOnAction(event -> installApp = installAppCheckBox.isSelected());
    }


    private void setPlatformComboBoxValues() {
        platformComboBox.getItems().addAll("Android", "iOS");
    }

    /**
     * Setting the Available devices according to the Platform selection
     */
    private void setDeviceComboBox() {
        installAppCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (platformComboBox.getSelectionModel().getSelectedItem() != null) {
                if (newValue) {
                    appComboBox.setVisible(true);

                    switch (platformComboBox.getValue()) {
                        case "Android":
                            for (File apk : AndroidHelper.getAvailableAPKs("/src/main/resources/apps/")) {
                                appComboBox.getItems().addAll(apk.toString());
                            }
                            break;

                        case "iOS":
                            for (File apk : IOSHelper.getAvailableIPAs("/src/main/resources/apps/")) {
                                appComboBox.getItems().addAll(apk.toString());
                            }
                            break;
                    }

                } else {
                    appComboBox.getItems().clear();
                    appComboBox.setVisible(false);
                }

            } else {
                Log.info("Please select desired Platform before selecting App to install");
            }
        });
    }

    /**
     * Enabling/Disabling App installation according to Platform selection
     */
    private void setAppComboBoxValues() {
        platformComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                switch (oldValue) {
                    case "Android":
                        deviceComboBox.getItems().clear();
                        break;

                    case "iOS":
                        deviceComboBox.getItems().clear();
                        break;
                }
            }

            if (newValue != null) {
                switch (newValue) {
                    case "Android":
                        deviceComboBox.getItems().addAll(AndroidHelper.getAndroidDeviceWithDetails());
                        break;

                    case "iOS":
                        deviceComboBox.getItems().addAll(IOSHelper.getIOSDevicesWithDetails());
                        break;
                }
            }
        });
    }

    public void setRunButtonFunctionality() {
        Log.info("Running Automation");
        getWindow().close();
    }

    private Stage getWindow() {
        return (Stage) borderPane.getScene().getWindow();
    }
}