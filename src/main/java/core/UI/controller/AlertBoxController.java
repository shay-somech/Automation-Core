package core.UI.controller;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class AlertBoxController {

    private AlertType alertType;
    private String title;
    private String message;

    public AlertBoxController(AlertType alertType, String title, String message) {
        this.alertType = alertType;
        this.title = title;
        this.message = message;
    }

    public void showAlert() {
        Alert alertBox = new Alert(alertType);
        alertBox.setTitle(title);
        alertBox.setHeaderText(null);
        alertBox.setContentText(message);
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.showAndWait();
        alertBox.setOnCloseRequest(Event::consume);
    }
}
