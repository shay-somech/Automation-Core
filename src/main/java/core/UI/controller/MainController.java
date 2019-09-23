package core.UI.controller;

import core.UI.application.UiSelection;
import core.UI.controller.tab.advancedTab.AdvancedController;
import core.UI.controller.tab.homeTab.HomeController;
import core.utils.Log;
import javafx.application.Platform;
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

    private UiSelection uiSelection;

    @FXML
    private BorderPane root;
    @FXML
    private HomeController homeController;
    private AdvancedController advancedController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.info("Launching Automation Interface ...");
        uiSelection = new UiSelection();
    }

    @FXML
    private void homeButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tab/Tab1.fxml"));
        AnchorPane anchor = loader.load();

        homeController = loader.getController();

        root.setCenter(anchor);
    }

    @FXML
    private void advancedSettingsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tab/Tab2.fxml"));
        Accordion accordion = loader.load();

        advancedController = loader.getController();

        root.setCenter(accordion);
    }

    @FXML
    private void runButtonClicked() {
        validateSelections();
        Platform.exit();
    }

    private void validateSelections() {
        if (uiSelection.getPlatform() == null) {
            new AlertBoxController(Alert.AlertType.ERROR, "No Platform selected", "Please set a Platform").showAlert();
            return;
        }

        if (uiSelection.getDevice().trim().isEmpty()) {
            new AlertBoxController(Alert.AlertType.ERROR, "No Device selected", "Please set a Device").showAlert();
            return;
        }

        if (uiSelection.getDevice().equals(uiSelection.getSecondDevice())) {
            new AlertBoxController(Alert.AlertType.ERROR, "Duplicate Devices", "Duplicate Devices Selected, please set a different Device").showAlert();
        }
    }
}
