package core.UI.controller;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.UI.controller.tab.Tab2Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private Tab2Controller tab2Controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Launching Automation Interface ...");
        tab1Controller.init(this);
        tab2Controller.init(this);
    }

    @FXML
    private void runButtonClicked() {
        if (validateSelections()) {
            tab1Controller.getSelections();
            tab2Controller.getSelections();
            Main.getWindow().close();
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
