package core.UI.controller;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.UI.controller.tab.Tab2Controller;
import core.utils.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private Tab2Controller tab2Controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.info("Launching Automation Interface ...");
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
