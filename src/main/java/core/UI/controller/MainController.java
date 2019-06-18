package core.UI.controller;

import core.UI.controller.tab.Tab1Controller;
import core.UI.controller.tab.Tab2Controller;
import core.constants.PlatformType;
import core.utils.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button Run;
    @FXML
    private BorderPane root;
    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private Tab2Controller tab2Controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.info("Launching Automation Interface ...");
        setKeyboardShortcuts();
    }

    private void setKeyboardShortcuts() {
        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.R,
                KeyCombination.SHORTCUT_ANY);

        final KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.N,
                KeyCombination.SHORTCUT_ANY);

        final KeyCombination keyComb3 = new KeyCodeCombination(KeyCode.A,
                KeyCombination.SHORTCUT_ANY);

        final KeyCombination keyComb4 = new KeyCodeCombination(KeyCode.I,
                KeyCombination.SHORTCUT_ANY);

        root.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (keyComb1.match(event)) {
                runButtonClicked();
            }

            if (keyComb2.match(event)) {
                if (tab1Controller.noReset.isSelected()) {
                    tab1Controller.noReset.setSelected(false);
                } else {
                    tab1Controller.noReset.setSelected(true);
                }
            }

            ObservableList<String> devices = tab1Controller.deviceComboBox.getItems();

            if (keyComb3.match(event)) {
                tab1Controller.platformComboBox.setValue(PlatformType.ANDROID);

                if (tab1Controller.platformComboBox.getValue().equals(PlatformType.ANDROID) && devices.size() > 0) {
                    tab1Controller.deviceComboBox.setValue(devices.get(0));
                }
            }

            if (keyComb4.match(event)) {
                tab1Controller.platformComboBox.setValue(PlatformType.IOS);

                if (tab1Controller.platformComboBox.getValue().equals(PlatformType.IOS) && devices.size() > 0) {
                    tab1Controller.deviceComboBox.setValue(devices.get(0));
                }
            }
        });
    }

    @FXML
    private void homeButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tab/Tab1.fxml"));
        AnchorPane anchor = loader.load();

        tab1Controller = loader.getController();

        root.setCenter(anchor);
    }

    @FXML
    private void advancedSettingsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tab/Tab2.fxml"));
        Accordion accordion = loader.load();

        tab2Controller = loader.getController();

        root.setCenter(accordion);
    }

    @FXML
    private void runButtonClicked() {
        if (validateSelections()) {
            tab1Controller.getSelections();
//            tab2Controller.getSelections();
            Stage window = (Stage) Run.getScene().getWindow();
            window.close();
        }
    }

    private boolean validateSelections() {
        if (tab1Controller.getPlatformSelection() == null) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "No Platform selected", "Please set a Platform");
            return false;
        }

        if (tab1Controller.getDeviceSelection() == null) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "No Device selected", "Please set a Device");
            return false;
        }

        if (tab1Controller.isDuplicateDevicesSet()) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "Duplicate Devices", "Duplicate Devices Selected, please set a different Device");
            return false;
        }

        return true;
    }
}
