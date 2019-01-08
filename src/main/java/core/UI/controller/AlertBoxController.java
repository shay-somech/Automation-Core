package core.UI.controller;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class AlertBoxController {

    public void alertBox(AlertType alertType, String title, String message) {
        Alert alertBox = new Alert(alertType);
        alertBox.setTitle(title);
        alertBox.setContentText(message);
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.showAndWait();
        alertBox.setOnCloseRequest(Event::consume);
    }
}
